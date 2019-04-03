package com.revature.aspects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	//log4j implementation TODO(fix appender not found problem)
//	private static Logger log = Logger.getLogger(LoggingAspect.class);
//	
//	//log all controller activity
//	@After("within(com.revature.controllers.*)")
//	public void logAfter(JoinPoint jp) {
//		//System.out.println(jp.getSignature()+" was called");
//		log.info(jp.getSignature()+" was called");
//	}
	
	//fileWriter as logger
	static String logFilePath = "src/log.txt";
	
	//log all controller activity
	@After("within(com.revature.controllers.*)")
	public void logAfter(JoinPoint jp) {
		System.out.println(jp.getSignature()+" was called");
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(logFilePath, true))){
			
			bw.write(jp.getSignature().getName()+" method was called");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
