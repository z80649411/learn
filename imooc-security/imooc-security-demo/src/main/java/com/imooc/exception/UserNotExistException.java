package com.imooc.exception;

/**
 * Created by 邓仁波 on 2017-10-31.
 */
public class UserNotExistException extends RuntimeException {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserNotExistException(String id) {
        super("user not exist");
        this.id=id;
    }
}
