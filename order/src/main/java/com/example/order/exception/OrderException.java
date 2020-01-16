package com.example.order.exception;

import com.example.order.enums.ResultEnum;

/**
 * 自定义异常
 */
public class OrderException  extends RuntimeException{

    private Integer code;

    public OrderException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
