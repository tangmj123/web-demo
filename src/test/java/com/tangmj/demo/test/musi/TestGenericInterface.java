package com.tangmj.demo.test.musi;

import java.lang.reflect.ParameterizedType;

import org.junit.Test;

import com.tangmj.demo.message.SaveUserHandler;

public class TestGenericInterface {

	@Test
	public void test(){
		ParameterizedType pt = (ParameterizedType) SaveUserHandler.class.getGenericInterfaces()[0];
		System.out.println(pt);
		@SuppressWarnings("rawtypes")
		Class type = (Class)pt.getActualTypeArguments()[0];
		System.out.println(type);
	}
}
