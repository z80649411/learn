package com.imooc.web.config;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.MyCallableInterceptor;
import com.imooc.web.interceptor.MyDeferredResultProcessingInterceptor;
import com.imooc.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 邓仁波 on 2017-10-31.
 */
//告诉spring这是一个配置类
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private TimeInterceptor timeInterceptor;
    @Autowired
    private MyCallableInterceptor myCallableInterceptor;
    @Autowired
    private MyDeferredResultProcessingInterceptor myDeferredResultProcessingInterceptor;

    //注册异步拦截器
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        /**
         //Callable方式异步返回请求
         configurer.registerCallableInterceptors(myCallableInterceptor);
         //DeferredResult方式异步返回请求
         configurer.registerDeferredResultInterceptors(myDeferredResultProcessingInterceptor);
         //设置超时时间
         configurer.setDefaultTimeout(1000);

         //Callable方式异步每次都会开一个新线程 没有线程池重用
         //可以通过下面的方法设置一个线程池
         ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
         taskExecutor.setCorePoolSize(5);
         taskExecutor.setMaxPoolSize(10);
         taskExecutor.setQueueCapacity(25);
         taskExecutor.initialize();
         configurer.setTaskExecutor(taskExecutor);

//         */
        super.configureAsyncSupport(configurer);
    }

    //注册同步拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor)
                .addPathPatterns("/user/*");
        super.addInterceptors(registry);
    }

    //注册过滤器
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        //设置路径
        urls.add("/user/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

}
