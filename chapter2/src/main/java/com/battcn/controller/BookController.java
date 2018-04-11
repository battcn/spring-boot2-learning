package com.battcn.controller;

import com.battcn.config.RabbitConfig;
import com.battcn.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/4/2 0002
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/books")
@Api(value = "消息队列", description = "消息队列", tags = {"1.0"})
public class BookController {

    private final AmqpTemplate rabbitTemplate;

    public BookController(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @ApiOperation("添加消息")
    @PostMapping
    public void defaultMessage(@RequestBody Book book) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book);
        this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, book);
    }


}
