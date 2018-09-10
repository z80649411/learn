package com.imooc.security.core.validate.code.sms;

/**
 * Created by 邓仁波 on 2017-11-13.
 * 手机验证码发送
 */
public interface SmsCodeSender {
    /**
     * 手机验证码发送逻辑
     * @param mobile
     * @param code
     */
    void send(String mobile,String code);
}
