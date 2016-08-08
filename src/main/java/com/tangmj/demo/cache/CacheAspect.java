package com.tangmj.demo.cache;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
@Order(0)
public class CacheAspect {
	private final Logger LOGGER =  LoggerFactory.getLogger(CacheAspect.class);
	@Autowired
	private RedisTemplate<String,Object> redisTemplate ;
	
	@Pointcut("@within(com.tangmj.base4j.cache.Cachable)")
	public void cachableOnType(){}
	
	@Pointcut("@annotation(com.tangmj.base4j.cache.Cachable)")
	public void cachableOnMethod(){}
	
	@Pointcut("execution(public * com.tangmj.base4j.service.impl.*.get*Id(Integer||int))")
	public void getById(){}
	
	@Pointcut("execution(public * com.tangmj.base4j.service.impl.*.query*Id(Integer||int))")
	public void queryById(){}
	
	@Around("(cachableOnMethod()&&@annotation(cache))||(cachableOnType()&&@within(cache)) &&(getById()||queryById())")
	public Object cachable(ProceedingJoinPoint pjp,Cachable cache) {
//		String key = getcacheKey(pjp,cache);
		String key = getCacheKey(pjp);
		Object value = null;
		ValueOperations<String, Object> opsForValue = null;
		try {
			opsForValue = redisTemplate.opsForValue();
			value = opsForValue.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("redis error!",e.getMessage(),e);
		}
		if(value != null) return value;
		try {
			value = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			LOGGER.error("JoinPoint.proceed error!",e.getMessage(),e);
		}
	    try {
	    	long expire = cache.expire();
		    if(expire <= 0) opsForValue.set(key, value);
		    else            opsForValue.set(key, value, expire, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("redis error!",e.getMessage(),e);
		}
	    return value;
	}
//  会执行两次
//	@Around("cachableOnType()&&@within(cache)&&(getById()||queryById())")
//	public Object cachableOnType(ProceedingJoinPoint pjp,Cachable cache) {
//		return cachableOnMethod(pjp, cache);
//	}
	
	
	@Pointcut("execution(public * com.tangmj.base4j.service.impl.*.update*(*))")
	public void update(){}
	
	@After("update()")
	public void updateAdvice(JoinPoint jp){
		Object record = jp.getArgs()[0];
		int id = 0;
		try {
			Method method = record.getClass().getMethod("getId");
			id = (int)method.invoke(record);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("get method of getId error!",e.getMessage(),e);
		}
		if(id<=0) return;
		String key = record.getClass().getName()+"."+id;
		ValueOperations<String, Object> opsForValue = null;
		try {
			opsForValue = redisTemplate.opsForValue();
			Object oldObject = opsForValue.get(key);
			if(oldObject!=null) opsForValue.set(key, record);
			long expire = opsForValue.getOperations().getExpire(key, TimeUnit.SECONDS);
			opsForValue.set(key, record, expire, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("redis error!",e.getMessage(),e);
		}
	}
	private String getcacheKey(ProceedingJoinPoint pjp, Cachable cache) {
		Signature signature = pjp.getSignature();
		String declaringTypeName = signature.getDeclaringTypeName();
		
		StringBuilder buf=new StringBuilder();  
        buf.append(declaringTypeName).append(".").append(signature.getName());  
        if(cache.key().length()>0) {  
            buf.append(".").append(cache.key());  
        }  
         
        Object[] args = pjp.getArgs();
        for(Object arg:args)
        	buf.append(".").append(arg.toString());
        return buf.toString();  
	}
	
	private String getCacheKey(ProceedingJoinPoint pjp){
//		try {
//			MethodInvocationProceedingJoinPoint mipj = (MethodInvocationProceedingJoinPoint)pjp;
//			Field methodInVocationField = mipj.getClass().getDeclaredField("methodInvocation");
//			methodInVocationField.setAccessible(true);
//			ReflectiveMethodInvocation rmi = (ReflectiveMethodInvocation)methodInVocationField.get(mipj);
//			Method method = rmi.getMethod();
//			Parameter[] parameters = method.getParameters();
//			Parameter parameter = parameters[0];
//			return method.getReturnType();
//		} catch ( SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
//			e.printStackTrace();
//		}
//		Object target = pjp.getTarget();
//		Object[] args = pjp.getArgs();
//		String methodName = pjp.getSignature().getName();
		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
//		Class<?>[] parameterTypes = ((MethodSignature) pjp.getSignature())
//				.getMethod().getParameterTypes();
//		
//		Parameter[] parameters = method.getParameters();
//		int paramsCount = args.length;
//		String key = null; 
//		
//		for(int i=0;i<paramsCount;i++){
//			String pName = parameters[i].getName();
//			Object pValue = args[i];
//			if(pName.equals("id")){
//				key = method.getReturnType().getClass()+"."+pValue;
//				break;
//			}
//		}
		Class<?> returnType = method.getReturnType();
		Object[] args = pjp.getArgs();
		return returnType.getName()+"."+args[0];
	}
}
