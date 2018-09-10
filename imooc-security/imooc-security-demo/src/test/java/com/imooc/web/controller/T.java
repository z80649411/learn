package com.imooc.web.controller;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 邓仁波 on 2017-11-2.
 */
public class T {
    @Test
    public void f() {
        RestTemplate template = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("date", "2017-11-8");
        String forObject = template.getForObject("https://javawind.net/p131", String.class, map);

        System.out.println(forObject);
    }

    @Test
    public void f1() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void steamTest() {
        new Thread(() -> System.out.println("lx nsbss !")).start();

        new Thread(() -> System.out.println("lx nsbss !")).start();
    }

    @Test
    public void f12() throws IOException {
        File file = new File("C:\\pic\\2.jpg");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        FileUtils.writeByteArrayToFile(new File("C:\\pic\\4.jpg"),bytes);
    }
}
