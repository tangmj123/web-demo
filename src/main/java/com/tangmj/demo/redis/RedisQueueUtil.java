package com.tangmj.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisQueueUtil{

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisQueueUtil.class);
	
	@Autowired private RedisTemplate redisTemplate;
	
	public <K,V> boolean push(K k,V v){
		try {
			ListOperations queue = redisTemplate.opsForList();
			queue.leftPush(k, v);
		} catch (Exception e) {
			LOGGER.error("push to redis queue error.",e.getMessage(),e);
			return false;
		}
		return true;
	}
	
	public <K,V> V pop(K k){
		try {
			ListOperations queue = redisTemplate.opsForList();
			return (V) queue.rightPop(k);
		} catch (Exception e) {
			return null;
		}
	}
	
	public <K,V>long size(K k){
		try {
			ListOperations queue = redisTemplate.opsForList();
			return queue.size(k);
		} catch (Exception e) {
			LOGGER.error("get size of redis queue error.",e.getMessage(),e);
		}
		return 0;
	}
}
