package com.battcn.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/4/16 0016
 */
@RestController
@RequestMapping("/api/redis")
@Api(tags = "1.0.0", value = "Redis管理", description = "Redis管理")
public class RedisController {

    private final RedisTemplate<String, String> redisCacheTemplate;

    @Autowired
    public RedisController(RedisTemplate<String, String> redisCacheTemplate) {
        this.redisCacheTemplate = redisCacheTemplate;
    }

    @ApiOperation("测试Redis存储")
    @GetMapping
    public String get(String key, String value) {

        redisCacheTemplate.opsForValue().set(key, value);
        return redisCacheTemplate.opsForValue().get(key);
    }


}
