package com.imooc.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by 邓仁波 on 2017-10-31.
 * 拦截器
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    //control方法调用之前调用 返回false 拦截
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle in");
        System.out.println("类"+((HandlerMethod) o).getBean().getClass().getName());
        System.out.println("方法名"+((HandlerMethod) o).getMethod().getName());
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        return true;
    }

    //control方法调用之后调用 抛出异常就不会调用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle in");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("TimeInterceptor 耗时:" + (new Date().getTime() - start));
    }

    //postHandle调用之后调用 一定会调用
    //Controller 错误处理器 调用在 afterCompletion 之前 如果写了某个异常的Controller 错误处理器则在这个方法拿不到异常
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion in");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("TimeInterceptor 耗时:" + (new Date().getTime() - start));
        System.out.println("ex: " + e);
    }
}
