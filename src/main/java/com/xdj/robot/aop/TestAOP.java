package com.xdj.robot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAOP {

    private final String execution_ = "execution(* com.xdj.robot.controller.*.*(..)";

    private final String annotation_ = "@annotation(com.xdj.robot.annotation.TestAnnotation)";

    @Pointcut(value = annotation_)
    private void testAop() {

    }

    @Before(value = "testAop()")
    public void beforeAop(JoinPoint joinPoint) {
        System.out.println("this is before aop advice");
    }



}
