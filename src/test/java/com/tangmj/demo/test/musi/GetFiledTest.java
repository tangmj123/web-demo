package com.tangmj.demo.test.musi;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;


public class GetFiledTest {

	@Test
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		ForTest forTest = new ForTest("tangmingjan");
		Field declaredField = forTest.getClass().getDeclaredField("test");
		declaredField.setAccessible(true);
		String testString = (String)declaredField.get(forTest);
		System.out.println(testString);
		
	}
	
	@Test
	public void test2() throws NoSuchFieldException, SecurityException{
		ProxyMethodInvocation methodInvocation = new ProxyMethodInvocation() {
			
			@Override
			public Object proceed() throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getThis() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public AccessibleObject getStaticPart() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object[] getArguments() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Method getMethod() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void setUserAttribute(String key, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setArguments(Object... arguments) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public MethodInvocation invocableClone(Object... arguments) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public MethodInvocation invocableClone() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getUserAttribute(String key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getProxy() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		MethodInvocationProceedingJoinPoint mpj = new MethodInvocationProceedingJoinPoint(methodInvocation);
		Field declaredField = mpj.getClass().getDeclaredField("methodInvocation");
	}
}

class ForTest{
	private final String test;
	
	public ForTest(String test){
		this.test = test;
	}
}
