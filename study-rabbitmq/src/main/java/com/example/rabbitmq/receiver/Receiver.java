package com.example.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 *
 * 演示RabbitMQ接收端
 */
@Component
public class Receiver {

    /**
     * 电脑类接收消息
     * @param hello
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myElectronics"),
            key = "computer",
            value = @Queue("computerElectronics")
    ))
    public void processComputer(String hello) {
        System.out.println("C-Receiver:" + hello);
    }

    /**
     * 手机类接收消息
     * @param hello
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myElectronics"),
            key = "phone",
            value = @Queue("phoneElectronics")
    ))
    public void processPhone(String hello) {
        System.out.println("P-Receiver:" + hello);
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
