package com.battcn.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ配置
 *
 * @author Levin
 * @since 2018/4/11 0011
 */
@Slf4j
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }

    public static final String DEFAULT_BOOK_QUEUE = "DEFAULT_BOOK_QUEUE";
    public static final String MANUAL_BOOK_QUEUE = "MANUAL_BOOK_QUEUE";
    public static final String DELAY_BOOK_QUEUE = "DELAY_BOOK_QUEUE";

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


    /**
     * 消息交换机的名字
     */
    public static final String BOOK_DELAY_EXCHANGE = "book_delay_exchange";

    /**
     * routing key 名称
     */
    public static final String BOOK_DELAY_ROUTING = "book_delay_routing";

    public static final String REGISTER_EXCHANGE_NAME = "test.user.register.exchange";
    public static final String REGISTER_DELAY_QUEUE_NAME = "test.user.register.delay.queue";

    public static final String REGISTER_DELAY_EXCHANGE_NAME = "test.user.register.delay.exchange";
    public static final String REGISTER_QUEUE_NAME = "test.user.register.queue";

    /**
     * 延迟队列配置
     **/
    @Bean(name = "registerDelayQueue")
    public Queue registerDelayQueue() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("x-dead-letter-exchange", REGISTER_EXCHANGE_NAME);
        params.put("x-dead-letter-routing-key", "all");
        return new Queue(REGISTER_DELAY_QUEUE_NAME, true, false, false, params);
    }

    @Bean
    public DirectExchange registerDelayExchange() {
        return new DirectExchange(REGISTER_DELAY_EXCHANGE_NAME);
    }

    @Bean
    public Binding registerDelayBinding() {
        return BindingBuilder.bind(registerDelayQueue()).to(registerDelayExchange()).with("");
    }

    /**
     * 指标消费队列配置
     **/
    @Bean
    public TopicExchange registerTopicExchange() {
        return new TopicExchange(REGISTER_EXCHANGE_NAME);
    }

    @Bean
    public Binding registerBinding() {
        return BindingBuilder.bind(registerQueue()).to(registerTopicExchange()).with("all");
    }

    @Bean(name = "registerQueue")
    public Queue registerQueue() {
        return new Queue(REGISTER_QUEUE_NAME, true);
    }


    /*@Bean
    public Queue delayBookQueue() {
        return QueueBuilder.durable(DELAY_BOOK_QUEUE)
                // DLX，dead letter发送到的exchange
                .withArgument("x-dead-letter-exchange", BOOK_DELAY_EXCHANGE)
                // dead letter携带的routing key
                .withArgument("x-dead-letter-routing-key", "all")
                .withArgument("x-message-ttl", 2 * 1000)
                .build();
    }


    @Bean
    public DirectExchange delayExchange() {
        // 只需简单一步开启延时消息，就是这么简单
        return new DirectExchange(BOOK_DELAY_EXCHANGE);
    }


    @Bean
    public Binding bindingNotify(Queue delayBookQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(delayBookQueue).to(delayExchange).with(BOOK_DELAY_ROUTING);
    }*/

}
