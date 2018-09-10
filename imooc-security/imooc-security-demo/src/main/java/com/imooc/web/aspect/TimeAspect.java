package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by 邓仁波 on 2017-10-31.
 * spring aop
 */
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("TimeAspect in");
        //获取参数
        Arrays.stream(pjp.getArgs()).forEach(o -> System.out.println("args:" + o));
        long time = new Date().getTime();
        //object为 调用方法返回值
        Object o = pjp.proceed();
        System.out.println("TimeAspect 耗时:" + (new Date().getTime() - time));
        System.out.println("TimeAspect out");
        return o;
    }
}
