package com.imooc.security.core.validate.code.sms;

/**
 * Created by 邓仁波 on 2017-11-13.
 * 默认手机验证码发送类
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("手机:"+mobile+"验证码："+code);
    }
}
