package com.tangmj.demo.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tangmj.demo.dao.mapper.UserMapper;
import com.tangmj.demo.dao.model.User;
@Component
@MessageSupport(threadPoolSize=5)
public class SaveUserHandler implements MessageHandler<User> {

	@Autowired private UserMapper userMapper;
	
	@Override
	public void handle(User user) {
		if(user == null) return;
		userMapper.insertSelective(user);
	}
}
