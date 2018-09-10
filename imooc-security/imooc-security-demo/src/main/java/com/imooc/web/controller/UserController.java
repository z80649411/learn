package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 邓仁波 on 2017-10-27.
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户controller", tags = {"用户操作接口"})
public class UserController {
    private Log log = LogFactory.getLog(getClass());
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public void regist(User user, HttpServletRequest request) {

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("/me")
    public Authentication getCurrentUser(Authentication authentication) {
        //用户名称
        log.info(authentication.getName());
        //用户信息
        return authentication;
    }

    @PutMapping("/{id:\\d+}")
    @JsonView(User.UserSimpleView.class)
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
//                FieldError fieldError = (FieldError) error;
//                String message = fieldError.getField() + error.getDefaultMessage();
//                log.info(message);
                log.info(error.getDefaultMessage());
            }
        }
        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));
        user.setId("1");
        return user;
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        log.info(ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));
        user.setId("1");
        return user;
    }

    //id:\d+ 正则表达式限制id只能为数字
    @DeleteMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public void delete(@PathVariable(name = "id") String id) {
        System.out.println(id);
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(@ApiParam(value = "用户名") @RequestParam String username, @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.JSON_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    //id:\d+ 正则表达式限制id只能为数字
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(name = "id") String id) {
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
//        throw new UserNotExistException(id);
//        throw new RuntimeException("RuntimeException");
        return user;
    }
}
