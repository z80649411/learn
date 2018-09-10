package com.dr.springbootcase.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by 邓仁波 on 2017-10-31.
 * 过滤器
 */
//注册成bean 方法一  用WebConfig类里面的注册 方法二

@Slf4j
@Component
public class EntryFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 打印请求日志
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if (!(requestURI.contains("swagger") || requestURI.contains("api"))) { //屏蔽swagger
            log.info("请求url: " + ((HttpServletRequest) servletRequest).getRequestURI());
            log.info("请求方式: " + ((HttpServletRequest) servletRequest).getMethod());
            String token = ((HttpServletRequest) servletRequest).getHeader("access_token");
            log.info("token: " + token);
            servletRequest.getParameterMap().forEach((k, v) -> {
                log.info("参数: " + k + " 值：" + Arrays.toString(v));
            });  //调用后面的过滤器 调用过滤器链
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
