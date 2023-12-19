package com.luv2code.springboot.Employee_Thymeleaf.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger myLogger = Logger.getLogger(getClass().getName());

//point cut declaration
    @Pointcut("execution(* com.luv2code.springboot.Employee_Thymeleaf.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.luv2code.springboot.Employee_Thymeleaf.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.luv2code.springboot.Employee_Thymeleaf.dao.*.*(..))")
    private void forDaoPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
       String theMethod = theJoinPoint.getSignature().toShortString();

       myLogger.info("=====>>in @Before: calling method: " + theMethod);

       Object[] args = theJoinPoint.getArgs();

       for (Object tempArgs : args){
           myLogger.info("====>>Argument:" + tempArgs);
       }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJointPoint, Object theResult){
        String theMethod = theJointPoint.getSignature().toShortString();

        myLogger.info("=====>>in @AfterReturnong: calling method: " + theMethod);

        myLogger.info("=====>> result:" + theResult );
    }
}
