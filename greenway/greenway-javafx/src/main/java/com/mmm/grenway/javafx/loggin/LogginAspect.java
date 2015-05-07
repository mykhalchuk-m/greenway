package com.mmm.grenway.javafx.loggin;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class LogginAspect {

//	@Pointcut("execution(* com.mmm.grenway.javafx.controller.LoginController.initialize())")
	public void initializePointcut() {
	}

//	@Before("initializePointcut()")
	public void loginControllerBeforeInitialization(JoinPoint joinPoint) {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.debug(String.format("Starting %s.initializing method", joinPoint.getTarget().getClass()));
	}

//	@After("initializePointcut()")
	public void loginControllerAfterInitialization(JoinPoint joinPoint) {
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.debug(String.format("Finishing %s.initializing method", joinPoint.getTarget().getClass()));
	}
}
