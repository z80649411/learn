package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by 邓仁波 on 2017-11-9.
 * 验证码创建接口
 */
public interface ValidateCodeGenerator {
    /**
     * 创建验证码并且返回
     * @param request
     * @return
     */
    ValidateCode generate(ServletWebRequest request);
}
