package com.imooc.security.core.social.qq.connet;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * Created by 邓仁波 on 2017-11-23.
 */
public class QQAdapter implements ApiAdapter<QQ> {
    /**
     * 测试当前api是否可用
     * @param api
     * @return 返回true表示连接可用
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues connectionValues) {
        QQUserInfo userInfo=api.getUserInfo();
        //名称
        connectionValues.setDisplayName(userInfo.getNickname());
        //头像
        connectionValues.setImageUrl(userInfo.getFigureurl_1());
        //首页
        connectionValues.setProfileUrl(null);
        //用户在qq的id
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    /**
     * 根据api获取一个标准的用户信息
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    /**
     * 发送消息
     * 比如像微博发送一条新的微博
     * 对于qq是多余的
     * @param api
     * @param message
     */
    @Override
    public void updateStatus(QQ api, String message) {

    }
}
