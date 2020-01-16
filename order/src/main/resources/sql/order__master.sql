-- 订单
-- 当执行update操作是，并且字段有ON UPDATE CURRENT_TIMESTAMP属性。则字段无论值有没有变化，它的值也会跟着更新为当前UPDATE操作时的时间。
CREATE TABLE `order_master`(
    `order_id`      VARCHAR(32) NOT NULL,
    `buyer_name`    VARCHAR(32) NOT NULL COMMENT '买家名字',
    `buyer_phone`   VARCHAR(32) NOT NULL COMMENT '买家电话',
    `buyer_address` VARCHAR(128) NOT NULL COMMENT '买家地址',
    `buyer_openid`  VARCHAR(64) NOT NULL COMMENT '买家微信openid',
    `order_amount`  DECIMAL(8,2) NOT NULL COMMENT '订单总金额',
    `order_status`  TINYINT(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认为新下单',
    `pay_status`    TINYINT(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认未支付',
    `create_time`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key(`order_id`),
    key `idx_buyer_openid` (`buyer_openid`)
);

-- 订单商品
CREATE TABLE `order_detail`
(
    `detail_id`        VARCHAR(32)   NOT NULL,
    `order_id`         VARCHAR(32)   NOT NULL,
    `product_id`       VARCHAR(32)   NOT NULL,
    `product_name`     VARCHAR(64)   NOT NULL COMMENT '商品名称',
    `product_price`    DECIMAL(8, 2) NOT NULL COMMENT '当前价格,单位分',
    `product_quantity` INT           NOT NULL COMMENT '数量',
    `product_icon`     VARCHAR(512)  COMMENT '小图',
    `create_time`      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    primary key(`detail_id`),
    key `idx_order_id` (`order_id`),
    FOREIGN KEY(`order_id`) REFERENCES order_master(`order_id`)
);