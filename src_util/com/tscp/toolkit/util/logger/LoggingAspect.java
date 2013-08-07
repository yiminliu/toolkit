package com.tscp.toolkit.util.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

	private final Log logger = LogFactory.getLog(this.getClass());
	
	//@Around("execution(* com.tscp.toolkit.web.controller.*.*(..))")
	//@Before("execution(* com.tscp.toolkit.web.controller..*.*(..))")
	@Around("execution(* com.tscp.toolkit.web.*.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object retVal = joinPoint.proceed();

		stopWatch.stop();
		
		StringBuilder logMessage = new StringBuilder();
		
		logMessage.append(".");
		logMessage.append(joinPoint.getSignature().getName());
		logMessage.append("(");
		// append args
		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			logMessage.append(args[i]).append(",");
		}
		if (args.length > 0) {
			logMessage.deleteCharAt(logMessage.length() - 1);
		}

		logMessage.append(")");
		logMessage.append(" execution time: ");
		logMessage.append(stopWatch.getTotalTimeMillis());
		logMessage.append(" ms");
		logger.info(logMessage.toString());
		return retVal;		
	}
	
	@AfterReturning(pointcut = "execution(* com.foo.bar..*.*(..))", returning = "retVal")
	public void logAfterMethod(JoinPoint joinPoint, Object retVal) {

	}
}



