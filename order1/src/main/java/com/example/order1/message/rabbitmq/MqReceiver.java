package com.example.order1.message.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * mq消息接收方
 */

@Slf4j
@Component
public class MqReceiver {

    /**
     * 1.第一种方式，不会自动创建队列，需要你自己到MQ的控制台手动创建
     * @param message
     */
    @RabbitListener(queues = "myQueue")
    public void process(String message) {
        log.info("MqReceiver:{}",message);
    }

    /**
     * 2.第二种方式,自动创建队列
     */
    @RabbitListener(queuesToDeclare = @Queue("myQueue1"))
    public void process2(String message){
        log.info("MqReceiver:{}",message);
    }

    /**
     * 3.自动创建，Exchange和Queue绑定
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue2"),
            exchange = @Exchange("myExchange")
    ))
    public void process3(String message){
        log.info("MqReceiver:{}",message);
    }


    /**
     * 数码供应商服务 接收消息
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void process4(String message){
        log.info("computer MqReceiver:{}",message);
    }
    /**
     * 水果供应商服务 接收消息
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void process5(String message){
        log.info("fruit MqReceiver:{}",message);
    }


    /**
     * 什么时候用Queue，什么时候用Exchange？
     * 情况方法4方法5
     *
     * 两个的exchange是一样的
     *
     * key和value不一样，
     *
     * 发送方：
     *  @Test
     * public void send3(){
     *    amqpTemplate.convertAndSend("myOrder","computer","now "+new Date());
     * }
     *  @Test
     * public void send4(){
     *    amqpTemplate.convertAndSend("myOrder","fruit","now "+new Date());
     * }
     * 将信息发送到不同的队列
     *
     */
}
