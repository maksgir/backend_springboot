package com.maksgir.tasklist.backend_springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Component
@Aspect
public class LoggingAspect {
    @Around("execution(* com.maksgir.tasklist.backend_springboot.controller.*.*(..))")
    public Object aroundAllRepositoryMethodsAdvise(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        String className = proceedingJoinPoint.toShortString();
        System.out.println(className);

        System.out.println("Begin: ");

        Object target = proceedingJoinPoint.proceed();

        System.out.println("End ");

        System.out.println("**********************************************");

        return target;
    }
}
