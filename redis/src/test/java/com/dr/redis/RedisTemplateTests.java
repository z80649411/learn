package com.dr.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private RedisTemplate<Object, Object> template;

    @Test
    public void contextLoads() {

        ValueOperations<Object, Object> objectObjectValueOperations = template.opsForValue();
        objectObjectValueOperations.set("d", "s");
        System.out.println(objectObjectValueOperations.get("d"));
    }

}
