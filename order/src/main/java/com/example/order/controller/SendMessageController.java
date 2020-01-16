package com.example.order.controller;

import com.example.order.dto.OrderDto;
import com.example.order.message.stream.StreamClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendMessageController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StreamClient streamClient;

    //@GetMapping("/sendMessage")
    //public void process(){
    //    String m = "now "+new Date();
    //    streamClient.output().send(MessageBuilder.withPayload(m).build());
    //}


    /**
     * 发送对象
     */
    @GetMapping("/sendMessage")
    public void process(){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId("123456");
        streamClient.output().send(MessageBuilder.withPayload(orderDto).build());
    }


    //发送到：手动建立的队列
    @GetMapping("/send")
    public void send(){
        amqpTemplate.convertAndSend("myQueue","now "+new Date());
    }

    //发送到：自动创建的队列
    @GetMapping("/send1")
    public void send1(){
        amqpTemplate.convertAndSend("myQueue1","now "+new Date());
    }

    //发送到：自动创建的队列，并且绑定
    @GetMapping("/send2")
    public void send2(){
        amqpTemplate.convertAndSend("myQueue2","now "+new Date());
    }

    @GetMapping("/send3")
    public void send3(){
        amqpTemplate.convertAndSend("myOrder","computer","now "+new Date());
    }
    @GetMapping("/send4")
    public void send4(){
        amqpTemplate.convertAndSend("myOrder","fruit","now "+new Date());
    }
}
