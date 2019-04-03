package com.revature.aspects;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	Date date = new Date();
	
	//log controller activity to console
	@AfterReturning(value="within(com.revature.controllers.*)", returning="returnVal")
	public void logAfter(JoinPoint jp, Object returnVal) {
		Object[] signatureArgs = jp.getArgs();
		System.out.println(date.toString()+" "+jp.getSignature()+" called");
		for(Object sigArg : signatureArgs) {
			System.out.println("	with arguments: "+sigArg);
		}
		System.out.println("	returned: "+returnVal.toString());
	}
	
	//log4j implementation TODO(fix appender not found problem)
//	private static Logger log = Logger.getLogger(LoggingAspect.class);
//	
//	//log all controller activity
//	@After("within(com.revature.controllers.*)")
//	public void logAfter(JoinPoint jp) {
//		System.out.println(jp.getSignature()+" was called");
//		log.info(jp.getSignature()+" was called");
//	}
	
	//fileWriter as logger
//	static String logFilePath = "src/log.txt";
//	
//	//log all controller activity
//	@After("within(com.revature.controllers.*)")
//	public void logAfter(JoinPoint jp) {
//		System.out.println(jp.getSignature()+" was called");
//		try(BufferedWriter bw = new BufferedWriter(new FileWriter(logFilePath, true))){
//			
//			bw.write(jp.getSignature().getName()+" method was called");
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
