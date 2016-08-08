package com.tangmj.demo.service;

import com.tangmj.demo.dao.model.User;

public interface IUserService {

	User save(User record);
	
	User getById(Integer id);
	
	User getWithId(Integer id);
	
	User queryUserById(int id);

	User queryByNoCache(Integer id);
	
	User queryById(Integer id,Integer id2);
	
	User update(User record);
	
	void deleteById(int id);
	
	
}
