package com.battcn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 *
 * @author Levin
 * @since 2018/4/11 0011
 */
@Slf4j
@Configuration
public class RabbitConfig {

    public static final String DEFAULT_BOOK_QUEUE = "DEFAULT_BOOK_QUEUE";
    public static final String MANUAL_BOOK_QUEUE = "MANUAL_BOOK_QUEUE";

    @Bean
    public Queue defaultBookQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(DEFAULT_BOOK_QUEUE, true);
    }

    @Bean
    public Queue manualBookQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(MANUAL_BOOK_QUEUE, true);
    }

}
