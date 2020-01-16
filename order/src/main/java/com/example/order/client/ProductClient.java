package com.example.order.client;

import com.example.order.dataobject.ProductInfo;
import com.example.order.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * feign的使用方法
 *
 * feign用于服务与服务之间的调用
 *
 * 用于调用另外一个服务[产品服务]
 */

@FeignClient(name = "product") //参数name,表示你要访问那个应用的接口
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDto> cartDtoList);
}
