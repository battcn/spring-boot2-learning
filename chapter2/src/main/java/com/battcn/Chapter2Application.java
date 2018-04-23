package com.battcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 */
@RestController
@SpringBootApplication
public class Chapter2Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

}
