package com.battcn.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @author Levin
 * @since 2018/9/30 0030
 */
public class Order {

    //@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime payTime;

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }
}
