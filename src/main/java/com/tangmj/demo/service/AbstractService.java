package com.tangmj.demo.service;

import org.apache.ibatis.annotations.Mapper;

public abstract class AbstractService<Record,Mapper> {

	public abstract Mapper getMapper();
	
	protected Record saveRecord(Record record){
		return null;
	};
	
	Record update(Record record){
		return null;
	};
	
	
}
