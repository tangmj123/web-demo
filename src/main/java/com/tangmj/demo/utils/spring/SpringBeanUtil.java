package com.tangmj.demo.utils.spring;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringBeanUtil implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public List<Object> getBeansByAnnotation(Class<? extends Annotation> annotationType){
		List<Object> beans  = new ArrayList<Object>();
		Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(annotationType);
		for(Entry<String,Object> entry : beansWithAnnotation.entrySet())
			beans.add(entry.getValue());
		return beans;
	}
	
	public List<Object> getBeansByInterface(Class<?> interfaceType){
		List<Object> beans  = new ArrayList<Object>();
		Map<String, ? extends Object> beansOfType = applicationContext.getBeansOfType(interfaceType);
		for(Entry<String, ? extends Object> entry : beansOfType.entrySet()) 
			beans.add(entry.getValue());
		return beans;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
