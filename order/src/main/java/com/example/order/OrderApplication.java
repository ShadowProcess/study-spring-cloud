package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@EnableFeignClients(basePackages = "com.example.order.client")    //feign:客户端负载均衡器
@EnableCircuitBreaker //circuit[电路] breaker[断路器]
@SpringBootApplication
@EnableDiscoveryClient //发现Eureka服务
//@SpringCloudApplication //里边包含cloud需要的基本注解，包含上面三个
@ComponentScan(basePackages = "com.example") //配置扫描范围
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
