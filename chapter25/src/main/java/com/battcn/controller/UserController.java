package com.battcn.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/6/28 0028
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public String get() {

        return "get.....";
    }

    /**
     * RequiresRoles 是所需角色 包含 AND 和 OR 两种
     * RequiresPermissions 是所需权限 包含 AND 和 OR 两种
     * @return msg
     */
    @RequiresRoles(value = {"admin", "test"},logical = Logical.OR)
    //@RequiresPermissions({"user:add"})
    @GetMapping("/query")
    public String query() {
        return "query.....";
    }

    @GetMapping("/find")
    public String find() {
        return "find.....";
    }
}
