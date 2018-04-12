package com.battcn.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
     * 延迟队列 TTL 名称
     */
    private static final String DELAY_QUEUE_PER_QUEUE_TTL_NAME = "DELAY_QUEUE_PER_QUEUE_TTL_NAME";
    /**
     * DLX，dead letter发送到的exchange
     */
    public static final String DELAY_EXCHANGE_NAME = "DELAY_EXCHANGE_NAME";
    /**
     * routing key 名称
     */
    public static final String DELAY_PROCESS_QUEUE_NAME = "DELAY_PROCESS_QUEUE_NAME";


    public static final String REGISTER_QUEUE_NAME = "test.book.register.queue";
    public static final String REGISTER_EXCHANGE_NAME = "test.book.register.exchange";
    public static final String REGISTER_PROCESS_QUEUE_NAME = "all";

    /**
     * 延迟队列配置
     **/
    @Bean
    public Queue delayProcessQueue() {
        Map<String, Object> params = Maps.newHashMap();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", REGISTER_EXCHANGE_NAME);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", REGISTER_PROCESS_QUEUE_NAME);
        return new Queue(DELAY_QUEUE_PER_QUEUE_TTL_NAME, true, false, false, params);
    }

    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayProcessQueue()).to(delayExchange()).with(DELAY_PROCESS_QUEUE_NAME);
    }


    @Bean
    public Queue registerBookQueue() {
        return new Queue(REGISTER_QUEUE_NAME, true);
    }

    /**
     * 指标消费队列配置
     **/
    @Bean
    public TopicExchange registerBookTopicExchange() {
        return new TopicExchange(REGISTER_EXCHANGE_NAME);
    }

    @Bean
    public Binding registerBookBinding() {
        // TODO 如果要让延迟队列之间有关联,这里的 routingKey 和 绑定的交换机很关键
        return BindingBuilder.bind(registerBookQueue()).to(registerBookTopicExchange()).with(REGISTER_PROCESS_QUEUE_NAME);
    }


}
