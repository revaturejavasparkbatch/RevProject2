package com.revature.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.revature.bean.User;

@Aspect
@Component
public class LoggingAspect {
	private static Logger log = Logger.getRootLogger();
	
	//logs argument values of loginUser method
	@Before(value="execution(ResponseEntity<User> loginUser(@RequestBody User user, HttpServletRequest request))")
	public void logBefore(JoinPoint jp) {
		log.info("User: "+jp.getArgs()[0]+" attempted to log in.");
	}
	
	//logs return values of loginUser method
	@AfterReturning(value="execution(ResponseEntity<User> loginUser(@RequestBody User user, HttpServletRequest request))", returning="returnVal")
	public void logAfter(JoinPoint jp, ResponseEntity<User> returnVal) {
		log.info("HTTP status code: "+returnVal.getStatusCodeValue()+", user object returned: "+returnVal.getBody());
	}
	
	
}
