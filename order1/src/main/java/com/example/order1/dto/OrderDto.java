package com.example.order1.dto;

import com.example.order1.dataobject.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 数据传输对象 [把两个参数，封装为一个]
 */
@Data
public class OrderDto {
    //订单id
    private String orderId;
    //买家名字
    private String buyerName;
    //买家手机号
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信OpenId
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态
    private Integer orderStatus;
    //支付状态
    private Integer payStatus;

    private List<OrderDetail> orderDetailList;
}
