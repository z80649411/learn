package com.dr.mybatiscase;

import com.dr.mybatiscase.entity.My;
import com.dr.mybatiscase.entity.UserEntity;
import com.dr.mybatiscase.mybatis.MyMapper;
import com.dr.mybatiscase.mybatis.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisCaseApplicationTests {
    @Autowired
    private MyMapper myMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void byXML() {
        My my = new My();
        my.setA("a");
        my.setB("b");
        myMapper.insert(my);
    }

    @Test
    public void byAnnotation() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(0L);
        userEntity.setUserCode("4");
        userEntity.setUserName("4");
        userEntity.setNickName("4");
        userEntity.setUserPwd("4");
        userEntity.setCreateDate(new Date());
        userEntity.setUpdateDate(new Date());
        userMapper.insertEntity(userEntity);
    }


}
