package com.example.order1.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class OrderDetail {
        @Id
        private String detailId;
        //订单id
        private String orderId;
        //商品id
        private String productId;
        //商品名
        private String productName;
        //商品价格
        private BigDecimal productPrice;
        //商品数量
        private Integer productQuantity;
        //商品图标
        private String productIcon;
        private Date createTime;
        private Date updateTime;
}
