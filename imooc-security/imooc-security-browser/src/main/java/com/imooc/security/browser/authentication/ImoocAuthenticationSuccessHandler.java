package com.imooc.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.security.core.properties.LoginType;
import com.imooc.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 邓仁波 on 2017-11-3.
 * 登录成功会调用
 */
@Component
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(HttpStatus.OK.value());
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }
    }
}
