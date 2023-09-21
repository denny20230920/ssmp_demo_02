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
public class RunSpeedAdvice {

    @Pointcut("execution(* com.demo.service.BookService.findBookByPage(..))")
    public void pt(){}

    @Around("RunSpeedAdvice.pt()")
    public Object runSpeed(ProceedingJoinPoint proceedingJoinPoint){

        Long start = System.currentTimeMillis();

        Object object = null;

        try {
            object = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            log.error("aop计算程序运行时间报错：",e);
            throw new RuntimeException(e);
        }

        Long end = System.currentTimeMillis();

        Long total = end - start;

        log.info("aop计算方法运行时间："+total+"ms");

        return object;
    }

}
