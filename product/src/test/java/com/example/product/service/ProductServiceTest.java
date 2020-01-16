package com.example.product.service;

import com.example.product.dto.CartDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest  {

    @Autowired
    private ProductService productService;

    @Test
    public void find(){
        productService.findUpAll().forEach(it-> System.out.println(it));
    }

    @Test
    public void findList(){
        productService.findList(Arrays.asList("157875196366160022","157875227953464068")).forEach(it-> System.out.println(it));
    }

    @Test
    public void decreaseStock() throws Exception{
        CartDto cartDto = new CartDto("157875196366160022",2);
        productService.decreaseStock(Arrays.asList(cartDto));
    }
}