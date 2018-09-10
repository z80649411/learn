package com.imooc.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by 邓仁波 on 2017-10-31.
 * 过滤器
 */
//注册成bean 方法一 只能设置对所有路径生效 要指定路径 用WebConfig类里面的方法二注册
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long time = new Date().getTime();
        //调用后面的过滤器 调用过滤器链
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("time filter 耗时:" + (new Date().getTime() - time));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
