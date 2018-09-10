package com.imooc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by 邓仁波 on 2017-11-2.
 * 重写登录验证
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录用户名:" + username);
        return buildUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户Id:" + userId);
        return buildUser(userId);
    }


    //    @Autowired
    //注册账号的时候用 passwordEncoder.encode() 加密
    //验证的时候 loadUserByUsername password直接放入加密前的 字符即可 不用自己解密
//    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException 用户不存在
     */
    private SocialUserDetails buildUser(String username) {
        logger.info("登录用户名: " + username);
        //可以注入jdbc redis等 获取用户详细信息
        //根据查找到的用户信息判断账号是否被冻结

        //$2a$10$2pnnzjVevYW0A.DAD2tfUO.RVzesPqEwmc/IJC896x9kqeqd.jNsS 为你数据库密码
        return new SocialUser(username, "$2a$10$DV7pz/zKri3NLSZY1R9.fuvUCQchrbEyMOnyKZEk7AuC1pqtX340."
                //账号可用，账号没过期,凭证没过期,账号没被锁定
                , true, true, true, true
                , AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
    }

}
