package com.battcn;

import com.battcn.interceptor.CacheKeyGenerator;
import com.battcn.interceptor.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * @author Levin
 */
@SpringBootApplication
public class Chapter22Application {

    public static void main(String[] args) {

        SpringApplication.run(Chapter22Application.class, args);

    }

    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }
}