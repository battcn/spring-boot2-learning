package com.battcn.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Levin
 * @since 2018/4/23 0023
 */
@Component
@PropertySource("classpath:my2.properties")
@ConfigurationProperties(prefix = "my2")
public class MyProperties2 {

    private int age;
    private String name;
    private String email;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MyProperties2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
