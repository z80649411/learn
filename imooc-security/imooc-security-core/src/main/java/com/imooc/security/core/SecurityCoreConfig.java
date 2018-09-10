package com.imooc.security.core;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by 邓仁波 on 2017-11-3.
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
    //登录密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        //BCryptPasswordEncoder 为Security实现的
        //也可以用自己的加密 方法实现PasswordEncoder接口 实现加密方法和 比较方法即可
        return new BCryptPasswordEncoder();
    }

}
