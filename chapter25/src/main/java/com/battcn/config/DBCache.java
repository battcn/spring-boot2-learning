package com.battcn.config;

import com.battcn.entity.User;

import java.util.*;

/**
 * 主要不想连接数据库..
 *
 * @author Levin
 * @since 2018/6/28 0028
 */
public class DBCache {


    /**
     * K 用户名
     * V 用户信息
     */
    public static final Map<String, User> USERS_CACHE = new HashMap<>();
    /**
     * K 角色ID
     * V 权限编码
     */
    public static final Map<String, Collection<String>> PERMISSIONS_CACHE = new HashMap<>();

    static {
        // TODO 假设这是数据库记录
        USERS_CACHE.put("u1", new User(1L, "u1", "p1", "admin", true));
        USERS_CACHE.put("u2", new User(2L, "u2", "p2", "admin", false));
        USERS_CACHE.put("u3", new User(3L, "u3", "p3", "test", true));

        PERMISSIONS_CACHE.put("admin", Arrays.asList("user:list", "user:add", "user:edit"));
        PERMISSIONS_CACHE.put("test", Collections.singletonList("user:list"));

    }


}
