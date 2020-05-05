package com.example.order1.enums;

import lombok.Getter;

@Getter
public enum PayStatus {
    WAIT(0,"等待支付"),
    SUCCESS(1,"已支付"),
    ;

    private Integer code;
    private String message;

    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
