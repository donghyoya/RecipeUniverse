package com.recipe.universe.global.aspect.trace;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceTraceAspect {

    @Pointcut("execution(* com.recipe.universe..*(..))")
    private void allInPackage(){}

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    private void annotationService(){}

    @Around("allInPackage() && annotationService()")
    public Object traceAllServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("[trace-start] {}", joinPoint.getSignature());
        Object proceed = joinPoint.proceed();
        log.info("[trace-end] {}", joinPoint.getSignature());
        return proceed;
    }
}
