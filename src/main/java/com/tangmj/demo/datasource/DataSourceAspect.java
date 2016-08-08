package com.tangmj.demo.datasource;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource(value="classpath:config.properties")
@Component
@Order(1)
public class DataSourceAspect {
	private final Logger LOGGER =  LoggerFactory.getLogger(DataSourceAspect.class);
	
	@Value("#{'${readMethods}'.split(',')}")
	private List<String> readMethods;
	
	@Pointcut("execution(* com.tangmj.demo.service.impl..*.*(..))")
	public void aspect(){}
	
	@Before("aspect()")
	public void before(JoinPoint jp){
		String methodName = jp.getSignature().getName();
		String dataSourceKey = "writeDataSource";
		for(String m:readMethods){
			if(methodName.startsWith(m)){
				dataSourceKey = "readDataSource";
				break;
			}
		}
//		if(readMethods.contains(methodName)) dataSourceKey = "readDataSource";
//		else                                dataSourceKey = "writeDataSource";
		
		LOGGER.debug("dataSourceKey is '{}'",dataSourceKey);
		
		DataSourceHandler.setDataSuourceKey(dataSourceKey);
			
	}
}
