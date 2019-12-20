package com.customer.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ServiceAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Before(value = "execution(* com.customer.service.CustomerServiceImpl.*(..))")
	public void before(JoinPoint jointpoint) {
		logger.info("Method Signature = " + jointpoint.getSignature());
	}
	
	@AfterReturning(value = "execution(* com.customer.service.CustomerServiceImpl.*(..))" , returning = "customerResponse")
	public void after(JoinPoint jointpoint , Object customerResponse) {
		logger.info("Response return by AOP = " + customerResponse);
	}
}
