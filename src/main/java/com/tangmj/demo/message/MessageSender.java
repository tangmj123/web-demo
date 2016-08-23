package com.tangmj.demo.message;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tangmj.demo.redis.RedisQueueUtil;

@Component
public class MessageSender<M> {

	@Autowired private RedisQueueUtil redisQueue;
	
	public boolean send(M message){
		if(message == null) return false;
		if(!(message instanceof Serializable)) return false;
		redisQueue.push(message.getClass().getName(), message);
		return true;
	}
}
