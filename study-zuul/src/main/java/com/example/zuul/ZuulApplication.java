package com.example.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}

/**
 * 如果不做任何配置
 *
 * 那么就可以路由到Eureka上已经注册的服务
 * 访问方法： http://localhost:8080/服务的名字/服务里边的接口地址
 *
 * 因为Zuul配置了Eureka的地址，也注册到了EurekaServer上了
 */
