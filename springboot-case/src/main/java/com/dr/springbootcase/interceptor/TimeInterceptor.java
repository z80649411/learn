package com.dr.springbootcase.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 邓仁波 on 2017-10-31.
 * 拦截器
 */
@Component
@Slf4j
public class TimeInterceptor implements HandlerInterceptor {


    //control方法调用之前调用 返回false 拦截
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("preHandle in");
        log.info("类" + ((HandlerMethod) o).getBean().getClass().getName());
        log.info("方法名" + ((HandlerMethod) o).getMethod().getName());
        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    //control方法调用之后调用 抛出异常就不会调用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle in");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        log.info("TimeInterceptor 耗时:" + (System.currentTimeMillis() - start));
    }

    //postHandle调用之后调用 一定会调用
    //Controller 错误处理器 调用在 afterCompletion 之前 如果写了某个异常的Controller 错误处理器则在这个方法拿不到异常
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion in");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        log.info("TimeInterceptor 耗时:" + (System.currentTimeMillis() - start));
        log.info("ex: " + e);
    }
}
