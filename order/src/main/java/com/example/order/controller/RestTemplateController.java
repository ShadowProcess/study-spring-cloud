package com.example.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Deprecated
//@RestController
public class RestTemplateController {

    //@Autowired
    //private LoadBalancerClient loadBalancerClient;

    //@Autowired
    //private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        //1.第一种方式(直接使用restTemplate,url写死)
        //RestTemplate restTemplate = new RestTemplate();
        //String response = restTemplate.getForObject("http://localhost:8080/msg",String.class);
        //log.info("response={}",response);


        //2.第二种方式(应用loadBalancerClient，通过应用名获取url，然后再使用RestTemplate)
        // [如果有多台提供相同服务的服务器处理办法]
        //ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        //String host = serviceInstance.getHost();
        //Integer port = serviceInstance.getPort();

        //String url = String.format("http://%s:%s", host, port)+"/msg";
        //RestTemplate restTemplate = new RestTemplate();
        //String response = restTemplate.getForObject(url,String.class);
        //log.info("response={}", response);



        //3.第三种方式(利用@LoadBalanced,可在restTemplate里直接使用名字)
        ///**
        // * 增加配置类：
        // * @Component
        // * public class RestTemplateConfig {
        // *
        // *     @Bean
        // *     @LoadBalanced
        // *     public RestTemplate restTemplate(){
        // *         return new RestTemplate();
        // *     }
        // * }
        // */
        //String response = restTemplate.getForObject("http://PRODUCT/msg", String.class); //PRODUCT 应用名
        //log.info("response={}", response);


        //////////////////////////使用Feign/////////////////////////////////////

        //log.info("response={}", response);
        //return response;
        return "";
    }
}
