package com.dr.springbootcase.config;


import com.dr.springbootcase.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by 邓仁波 on 2017-10-31.
 */
//告诉spring这是一个配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TimeInterceptor timeInterceptor;


    //注册同步拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor)
                .addPathPatterns("/**").//添加拦截路径
                excludePathPatterns("/error");//排除spring自带的错误跳转url
    }


}
