package com.example.order.message.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    @Input("myMessage")
    SubscribableChannel input();

    @Output("myMessage")
    MessageChannel output();

    @Input("myMessageDemo")
    SubscribableChannel input0();
    @Output("myMessageDemo")
    MessageChannel output0();


    @Input("myMessageLol")
    SubscribableChannel input1();
    @Output("myMessageLol")
    MessageChannel output1();

}
