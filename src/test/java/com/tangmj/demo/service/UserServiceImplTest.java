package com.tangmj.demo.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tangmj.demo.dao.model.User;
import com.tangmj.demo.service.impl.UserServiceImpl;
import com.tangmj.demo.test.base.BaseTest;

public class UserServiceImplTest extends BaseTest{

	@Autowired private UserServiceImpl userService;
	
	@Test
	public void save() {
		User record = new User();
		record.setAccount("1056617205@qq.com");
		record.setCreateTime(new Date());
		record.setDeptId(1);
		record.setMail("18721709753@163.com");
		record.setPassword("admin");
		record.setPhone("18721709753");
		record.setSex(0);
		record.setUserType(0);
		userService.save(record);
	}

	@Test
	public void getById() {
		User user = userService.getById(2);
		org.junit.Assert.assertNotNull(user);
	}
	
	@Test
	public void getWithId(){
		User user = userService.getWithId(1);
		org.junit.Assert.assertNotNull(user);
	}
	@Test
	public void queryUserById(){
		User user = userService.queryUserById(2);
		org.junit.Assert.assertNotNull(user);
	}
	
	@Test
	public void queryByNoCache(){
		User user = userService.queryByNoCache(2);
		org.junit.Assert.assertNotNull(user);
	}
	
	@Test
	public void  queryById(){
		userService.queryById(1, 0);
	}
	
	@Test
	public void update(){
		User record = new User();
		record.setId(2);
		record.setAccount("1056617205@qq.com_update");
		record.setCreateTime(new Date());
		record.setDeptId(1);
		record.setMail("18721709753@163.com_update");
		record.setPassword("admin_update");
		record.setPhone("18721709753_update");
		record.setSex(0);
		record.setUserType(0);
		userService.update(record);
	}
}
