package com.example.product.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.product.dataobject.ProductInfo;
import com.example.product.dto.CartDto;
import com.example.product.enums.ProductStatusEnum;
import com.example.product.enums.ResultEnum;
import com.example.product.exception.ProductException;
import com.example.product.repository.ProductInfoRepository;
import com.example.product.service.ProductService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired //根据beanType注入
    private ProductInfoRepository productInfoRepository;

    @Autowired //byType注入
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    @Override
    public void decreaseStock(List<CartDto> cartDtoList) {
        List<ProductInfo> productInfoList = decreaseStockProcess(cartDtoList);
        //发送MQ消息 => 订单服务
        amqpTemplate.convertAndSend("productInfo", JSONObject.toJSON(productInfoList));
    }


    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<CartDto> cartDtoList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (CartDto cartDto : cartDtoList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDto.getProductId());

            //判断商品是否存在
            if (!productInfoOptional.isPresent()){
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            ProductInfo productInfo = productInfoOptional.get();

            //判断库存是否足够
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0) {
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }



}
