package com.battcn.controller;

import com.battcn.pojo.Book;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 参数校验
 *
 * @author Levin
 * @since 2018/6/04 0031
 */
@Validated
@RestController
public class ValidateController {

    @GetMapping("/test1")
    public String test1(String name) {
        if (name == null) {
            throw new NullPointerException("name 不能为空");
        }
        if (name.length() < 2 || name.length() > 10) {
            throw new RuntimeException("name 长度必须在 2 - 10 之间");
        }
        return "success";
    }

    @GetMapping("/test2")
    public String test2(@NotBlank(message = "name 不能为空") @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name) {
        return "success";
    }

    @GetMapping("/test3")
    public String test3(@Validated Book book) {
        return "success";
    }


}
