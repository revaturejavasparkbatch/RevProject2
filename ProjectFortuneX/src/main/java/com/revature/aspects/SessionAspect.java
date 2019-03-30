package com.revature.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.revature.bean.User;

@Aspect
@Component
public class SessionAspect {

	@AfterReturning("execution(session getSession())")
	public void populatingSession(JoinPoint jp) {
		User user = (User) jp.getTarget();
		user.getEmail();
	}
	
}
