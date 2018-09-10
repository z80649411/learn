package com.dr.redis;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * Created by 邓仁波 on 2018-3-15.
 */
public class redisTest {
    @Test
    public void redissonTest() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://123.206.62.172:6799").setPassword("Drb.199519.cq");
        RedissonClient redisson = Redisson.create(config);
        RMapCache<String, String> usersCache = redisson.getMapCache("test");
        usersCache.put("s", "s");
        Set<Map.Entry<String, String>> set = usersCache.entrySet();
        set.forEach(e -> {
            System.out.println(e.toString());
        });
        redisson.shutdown();
    }

    @Test
    public void jedisTest() {
        Jedis jedis = new Jedis("123.206.62.172", 6799);
//        System.out.println(jedis.hgetAll("wxzsk:user:map"));
        jedis.auth("Drb.199519.cq");
        jedis.set("n", "m");
        System.out.println(jedis.get("n"));
        jedis.close();
    }
}
