package com.tangmj.demo.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface Cachable {
	
	public enum KeyMode{  
		DEFAULT,    //只有加了@CacheKey的参数,才加入key后缀中  
		BASIC,      //只有基本类型参数,才加入key后缀中,如:String,Integer,Long,Short,Boolean  
		ALL;        //所有参数都加入key后缀  
	}  
	public String key() default "";
	
	public long expire() default 0;
	
	public KeyMode keyMode() default KeyMode.DEFAULT;       //key的后缀模式  
}
