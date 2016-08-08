package com.tangmj.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tangmj.demo.cache.Cachable;
import com.tangmj.demo.dao.mapper.RoleMapper;
import com.tangmj.demo.dao.model.Role;
import com.tangmj.demo.service.IRoleService;
@Transactional
@Service
@Cachable
public class RoleServiceImpl implements IRoleService{

	@Autowired private RoleMapper roleMapper;
	
	@Override
	public Role save(Role record) {
		roleMapper.insertSelective(record);
		return record;
	}

	@Override
	public Role getById(Integer id) {
		return roleMapper.selectByPrimaryKey(id);
	}

}
