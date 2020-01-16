package com.example.order;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Mq 发送端
 */

@Component
public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //发送到：手动建立的队列
    @Test
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now "+new Date());
    }


    //发送到：自动创建的队列
    @Test
    public void send1(){
        amqpTemplate.convertAndSend("myQueue1","now "+new Date());
    }

    //发送到：自动创建的队列，并且绑定
    @Test
    public void send2(){
        amqpTemplate.convertAndSend("myQueue2","now "+new Date());
    }


    @Test
    public void send3(){
        amqpTemplate.convertAndSend("myOrder","computer","now "+new Date());
    }
    @Test
    public void send4(){
        amqpTemplate.convertAndSend("myOrder","fruit","now "+new Date());
    }


}
