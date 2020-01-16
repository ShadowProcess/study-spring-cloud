package com.example.product.service;

import com.example.product.dataobject.ProductInfo;
import com.example.product.dto.CartDto;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     * @param cartDtoList
     */
    void decreaseStock(List<CartDto> cartDtoList);
}
