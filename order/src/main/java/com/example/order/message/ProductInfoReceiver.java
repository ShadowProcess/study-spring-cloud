package com.example.order.message;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.order.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        //message => ProductInfo

        List<ProductInfo> productInfo = JSONObject.parseObject(message, new TypeReference<List<ProductInfo>>() {});
        log.info("从队列【{}】接收到消息：{}", "productInfo", productInfo);

        for (ProductInfo info : productInfo) {
            //存储到Redis中
            redisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, info.getProductId()),
                    info.getProductStock().toString());
        }
    }
}
