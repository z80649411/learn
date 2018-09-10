package com.imooc.security.core.properties;

/**
 * Created by 邓仁波 on 2017-11-9.
 */
public class ImageCodeProperties extends SmsCodeProperties {
    public ImageCodeProperties() {
        setLength(4);
    }

    //验证码宽度
    private int width = 67;
    //验证码高度
    private int height = 23;
 //拦截url
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
