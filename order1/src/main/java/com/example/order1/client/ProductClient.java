package com.example.order1.client;

import com.example.order1.dataobject.ProductInfo;
import com.example.order1.dto.CartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
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

@FeignClient(name = "product",fallback = ProductClient.ProductClientFallback.class) //参数name,表示你要访问那个应用的接口
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDto> cartDtoList);


    @Component
    static class ProductClientFallback implements ProductClient {
        @Override
        public String productMsg() {
            return null;
        }
        /**
         * 如果触发服务降级，那么会调用这个方法
         * @param productIdList
         * @return
         */
        @Override
        public List<ProductInfo> listForOrder(List<String> productIdList) {
            return null;
        }
        @Override
        public void decreaseStock(List<CartDto> cartDtoList) {

        }
    }
}
