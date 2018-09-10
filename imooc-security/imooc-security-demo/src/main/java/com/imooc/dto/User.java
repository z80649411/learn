package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by 邓仁波 on 2017-10-27.
 */
public class User {
    @JsonView(UserSimpleView.class)
    private String id;
    @JsonView(UserSimpleView.class)
    @Past(message = "生日必须是过去的时间")
    private Date birthday;
    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;
    @JsonView(UserSimpleView.class)
    @MyConstraint(message = "这是一个测试")
    @ApiModelProperty(value = "用户名字")
    private String username;

    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
