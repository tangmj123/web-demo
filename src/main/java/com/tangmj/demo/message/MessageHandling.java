package com.tangmj.demo.message;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.tangmj.demo.redis.RedisQueueUtil;


@Component
public class MessageHandling implements ApplicationContextAware{

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandling.class);
	
	private ApplicationContext applicationContext;
	
	@Autowired private RedisQueueUtil redisQueue;
	
	@PostConstruct
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access", "null" })
	public void handle(){
		Map<String, Object> handlers = applicationContext.getBeansWithAnnotation(MessageSupport.class);
		if(handlers == null && handlers.size() == 0) return;
		for(Entry<String, Object> entry : handlers.entrySet()){
			Class<?> handler = entry.getValue().getClass();
			String key = getKey(handler);
			MessageSupport messageSupport = handler.getAnnotation(MessageSupport.class);
			int poolSize = messageSupport.threadPoolSize();
			LOGGER.debug("starting deal with channel : "+key+" ,poolSize: "+poolSize);
			Executor pool = Executors.newFixedThreadPool(poolSize);
			for(int i=0;i < poolSize; i++){
				pool.execute(()->{
					while(true){
						if(redisQueue.size(key)==0){
							LOGGER.debug("channel of "+key +" is empty.");
							try {
								Thread.currentThread().sleep(500);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						MessageHandler messageHandler = (MessageHandler)entry.getValue();
						messageHandler.handle(redisQueue.pop(key));
					}
				});
			}
		}
	}

	
	
	private String getKey(Class<?> handler) {
		try {
    		ParameterizedType pt = (ParameterizedType) handler.getGenericInterfaces()[0];
    		@SuppressWarnings("rawtypes")
			Class type = (Class)pt.getActualTypeArguments()[0];
    		return type.getName();
		} catch (Exception e) {
			return null;
		}
	}



	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
