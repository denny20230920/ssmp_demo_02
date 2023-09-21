package com.demo.controller.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogRequestResponseAdvice {

    @Pointcut("execution(* com.demo.controller.*Controller.*(..))")
    public void pt(){}

    //@Around("LogRequestResponseAdvice.pt()")
    public void addLog(ProceedingJoinPoint proceedingJoinPoint){

        Object[] args = proceedingJoinPoint.getArgs();
        String[] stringArgs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            stringArgs[i] = args[i].toString();
            System.out.println(stringArgs[i]);
        }

        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        System.out.println(proceed.toString());
    }

}
