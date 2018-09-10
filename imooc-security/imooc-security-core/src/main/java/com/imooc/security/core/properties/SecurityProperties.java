package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 邓仁波 on 2017-11-3.
 * 读取配置文件
 */

@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private BrowserProperties browser = new BrowserProperties();
    private SocialProperties social = new SocialProperties();

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
