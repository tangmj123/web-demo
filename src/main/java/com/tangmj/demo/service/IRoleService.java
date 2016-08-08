package com.tangmj.demo.service;

import com.tangmj.demo.dao.model.Role;

public interface IRoleService {

	Role save(Role record);
	
	Role getById(Integer id);
}
