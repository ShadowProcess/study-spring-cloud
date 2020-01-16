package com.example.product.service;

import com.example.product.ProductApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 另外一种测试类写法
 */

@Component
public class CategoryServiceTest extends ProductApplicationTests {

    @Autowired //byType注入
    private CategoryService categoryService;

    @Test
    void findByCategoryTypeIn() {
        categoryService.findByCategoryTypeIn(Arrays.asList(11,12)).forEach(it-> System.out.println(it));
    }
}