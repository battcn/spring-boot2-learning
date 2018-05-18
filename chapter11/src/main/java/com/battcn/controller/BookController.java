package com.battcn.controller;

import com.battcn.config.RabbitConfig;
import com.battcn.entity.Book;
import com.battcn.handler.BookHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/4/2 0002
 */
@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public BookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book); 对应 {@link BookHandler#listenerAutoAck}
     * this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, book); 对应 {@link BookHandler#listenerManualAck}
     */
    @GetMapping
    public void defaultMessage() {
        Book book = new Book();
        book.setId("1");
        book.setName("一起来学Spring Boot");
        this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, book);
        this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_BOOK_QUEUE, book);
    }


}
