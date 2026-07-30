package com.varun.SpringBootDemoJpa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionAspect {
    @Around("execution(* com.varun.SpringBootDemoJpa.service.*.*(..))")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } catch (Exception ex) {
            System.out.println("Exception occurred in method: "
                    + joinPoint.getSignature().getName());

            System.out.println("Exception message: " + ex.getMessage());

            throw ex;
        }
        finally {
            long endTime = System.currentTimeMillis();

            System.out.println("Execution time: "
                    + (endTime - startTime) + " ms");
        }
    }
}
