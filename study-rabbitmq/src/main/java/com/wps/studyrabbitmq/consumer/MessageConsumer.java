package com.wps.studyrabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Title MessageConsumer
 * @Description
 * @auther wps
 * @Date 2020/7/1817:55
 */
@Component
@RabbitListener(queues="hello")
public class MessageConsumer {
    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    //监听器监听指定的Queue
    @RabbitHandler
    public void process(String hello){
        log.info("Receiver:"+hello);
        System.out.println("消息接受成功");

    }


}
