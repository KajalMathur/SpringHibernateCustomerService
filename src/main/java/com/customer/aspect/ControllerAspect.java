package com.customer.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ControllerAspect {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* com.customer.controller.CustomerController.*(..))")
	public Object around(ProceedingJoinPoint jointpoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object custRsp = jointpoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Total time taken by request = " + timeTaken);
		return custRsp;
	}
}
