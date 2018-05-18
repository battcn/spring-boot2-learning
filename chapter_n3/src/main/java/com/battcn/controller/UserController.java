package com.battcn.controller;

import com.battcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/5/17 0017
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public int insert(){
        return userService.insert();
    }

}
