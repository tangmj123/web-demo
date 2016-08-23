package com.tangmj.demo.message;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MessageSupport {

	public int threadPoolSize() default 1;
}
