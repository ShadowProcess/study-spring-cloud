package com.example.product.repository;

import com.example.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus(){
        List<ProductInfo> byProductStatus = productInfoRepository.findByProductStatus(0);
        Assert.assertTrue(byProductStatus.size()>0);
        byProductStatus.forEach(it->System.out.println(it));
    }

    @Test
    public void tt(){
        List<ProductInfo> byProductIdIn = productInfoRepository.findByProductIdIn(Arrays.asList("157875196366160022","157875227953464068"));
        byProductIdIn.forEach(it-> System.out.println(it));
    }
}