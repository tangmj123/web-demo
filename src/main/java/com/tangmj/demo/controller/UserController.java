package com.tangmj.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tangmj.demo.dao.model.User;
import com.tangmj.demo.service.IUserService;

@Controller
@RequestMapping("api/user")
public class UserController {

	@Autowired private IUserService userService;
	
	@RequestMapping("read")
	@ResponseBody
	public User testReadDataSource(){
		return userService.getById(2);
	}
	
	@RequestMapping("write")
	@ResponseBody
	public User testWriteDataSource(){
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
		return record;
	}
}
