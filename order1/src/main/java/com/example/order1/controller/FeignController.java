package com.example.order1.controller;

import com.example.order1.client.ProductClient;
import com.example.order1.dataobject.ProductInfo;
import com.example.order1.dto.CartDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class FeignController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        String response = productClient.productMsg();
        log.info("【Feign】 response={}",response);
        return response;
    }

    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfos = productClient.listForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
        log.info("response={}",productInfos);
        return "ok";
    }

    @GetMapping("/productDecreaseStock")
    public String productDecreaseStock(){
        productClient.decreaseStock(Arrays.asList(new CartDto("157875196366160022",3)));
        log.info("ok");
        return "ok";
    }
}
