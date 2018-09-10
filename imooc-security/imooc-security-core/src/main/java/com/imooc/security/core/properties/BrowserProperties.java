package com.imooc.security.core.properties;

import static com.imooc.security.core.properties.SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

/**
 * Created by 邓仁波 on 2017-11-3.
 */
public class BrowserProperties {

    private String signUpUrl = "/imooc-signUp.html";

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    private String loginPage = DEFAULT_LOGIN_PAGE_URL;
    private LoginType loginType = LoginType.JSON;
    //记住我的持续时间 秒
    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}

