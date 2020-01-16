package com.example.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试配置是否为分布式配置
 */

@RestController
@RequestMapping("/env")
@RefreshScope //
public class EnvController {

    @Value("${env}")  //env参数是:profiles    {name}-{profiles}.yml
    private String env;

    @GetMapping("/print")
    public String print(){
        return env;
    }
}
