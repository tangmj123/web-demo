package com.tangmj.demo.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tangmj.demo.dao.model.Role;
import com.tangmj.demo.service.IRoleService;
import com.tangmj.demo.test.base.BaseTest;

public class RoleServiceImplTest extends BaseTest{

	@Autowired private IRoleService roleService;
	@Test
	public void testSave() {
		Role role = new Role();
		role.setRoleName("admin1");
		role.setRoleType(0);
		roleService.save(role);
	}

	@Test
	public void testGetById() {
		Role role = roleService.getById(1);
		System.out.println(role);
	}

}
