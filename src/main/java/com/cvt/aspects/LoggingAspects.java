package com.cvt.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspects {

    Logger logger=Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.cvt.config.*.*(..))")
    public void configAspect(){}

    @Pointcut("execution(* com.cvt.dao.*.*(..))")
    public void daoAllAspect(){}

    @Before("configAspect()")
    public void beforeConfigMethod(JoinPoint joinPoint){
        logger.info(">> "+joinPoint.getSignature().getName() + "is called ...");
    }

    @After("daoAllAspect()")
    public void afterDaoMethod(JoinPoint joinPoint){
        logger.info(">> "+joinPoint.getSignature().getName() + "is called ...");
    }
}
