package com.tangmj.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tangmj.demo.cache.Cachable;
import com.tangmj.demo.dao.mapper.UserMapper;
import com.tangmj.demo.dao.model.User;
import com.tangmj.demo.service.IUserService;
@Transactional
@Service
@Cachable(expire=1000)
public class UserServiceImpl implements IUserService {

	@Autowired private UserMapper userMapper;
	
	
	@Override
	public User save(User record) {
		userMapper.insertSelective(record);
		return record;
	}

	@Cachable(expire=10)
	@Override
	public User getById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public User getWithId(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User queryUserById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}
	@Override
	public User queryByNoCache(Integer id){
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User queryById(Integer id, Integer id2) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	protected User queryById(int id){
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User update(User record) {
		userMapper.updateByPrimaryKeySelective(record);
		return record;
	}

	@Override
	public void deleteById(int id) {
		
	}
}
