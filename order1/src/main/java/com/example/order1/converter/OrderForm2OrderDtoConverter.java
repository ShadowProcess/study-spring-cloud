package com.example.order1.converter;

import com.example.order1.dataobject.OrderDetail;
import com.example.order1.dto.OrderDto;
import com.example.order1.enums.ResultEnum;
import com.example.order1.exception.OrderException;
import com.example.order1.from.OrderFrom;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Gson转换工具
 */
@Slf4j
public class OrderForm2OrderDtoConverter {

    public static OrderDto convert(OrderFrom orderFrom) {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderFrom.getName());
        orderDto.setBuyerPhone(orderFrom.getPhone());
        orderDto.setBuyerAddress(orderFrom.getAddress());
        orderDto.setBuyerOpenid(orderFrom.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();

        Gson gson = new Gson();
        try {
            //第一个参数字符串，第二个参数是你要转换的类型
            orderDetailList = gson.fromJson(orderFrom.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e) {
            log.error("【json转换】错误，string={}",orderFrom.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
