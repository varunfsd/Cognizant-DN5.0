package com.varun.SpringBootDemoJpa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(ExceptionAspect.class);

    @Around("execution(* com.varun.SpringBootDemoJpa.service.*.*(..))")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } catch (Exception ex) {

            logger.error(
                    "Exception occurred in method: {}",
                    joinPoint.getSignature().getName(),
                    ex
            );

            throw ex;

        } finally {

            long endTime = System.currentTimeMillis();

            logger.info(
                    "Method '{}' executed in {} ms",
                    joinPoint.getSignature().getName(),
                    (endTime - startTime)
            );
        }
    }
}