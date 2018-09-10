package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 邓仁波 on 2017-11-13.
 * 验证码处理器，封装不同验证码的处理逻辑
 */
public interface ValidateCodeProcessor {
    //验证码放入session的前缀
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 验证码校验
     * @param request
     */
    void validate(ServletWebRequest request);
}
