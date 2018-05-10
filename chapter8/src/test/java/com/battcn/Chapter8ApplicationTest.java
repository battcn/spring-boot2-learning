package com.battcn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Levin
 * @since 2018/5/10 0010
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter8ApplicationTest {


    private static final Logger log = LoggerFactory.getLogger(Chapter8ApplicationTest.class);

    @Autowired
    private RedisTemplate<String, String> redisCacheTemplate;

    @Test
    public void get() {
        // TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "battcn:user:1";
        redisCacheTemplate.opsForValue().set(key, "10086");
        // TODO 对应 String（字符串）
        final String value = redisCacheTemplate.opsForValue().get(key);
        log.info("[缓存结果] - [{}]", value);
        // TODO 对应 ZSet（有序集合）
        redisCacheTemplate.opsForZSet();
        // TODO 对应 Hash（哈希）
        redisCacheTemplate.opsForHash();
        // TODO 对应 List（列表）
        redisCacheTemplate.opsForList();
        // TODO 对应 Set（集合）
        redisCacheTemplate.opsForSet();
        // TODO 对应 GEO（地理位置）
        redisCacheTemplate.opsForGeo();

    }


}
