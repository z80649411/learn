package com.imooc.security.core.support;

/**
 * Created by 邓仁波 on 2017-11-3.
 * 返回实体
 */
public class SimpleResponse {
    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
