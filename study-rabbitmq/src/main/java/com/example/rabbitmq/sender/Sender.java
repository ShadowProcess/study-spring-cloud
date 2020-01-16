package com.example.rabbitmq.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


/**
 * @author Alex
 *
 * 演示RabbitMQ发送端
 */

@Component
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;


    @Test
    public void sendComputer() {
        for (int i = 0; i < 30; i++) {
            String content = "发送信息到电脑队列，当前时间：" + new Date();
            this.rabbitmqTemplate.convertAndSend("myElectronics", "computer", content);
        }
    }


    @Test
    public void sendPhone() {
        for (int i = 0; i < 30; i++) {
            String content = "发送信息到手机队列，当前时间：" + new Date();
            this.rabbitmqTemplate.convertAndSend("myElectronics", "phone", content);
        }
    }
}
