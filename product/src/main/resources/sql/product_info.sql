TRUNCATE TABLE `product_info`;
CREATE TABLE `product_info`
(
    `product_id`          VARCHAR(32)   NOT NULL,
    `product_name`        VARCHAR(64)   NOT NULL COMMENT '商品名称',
    `product_price`       DECIMAL(8, 2) NOT NULL COMMENT '单价',
    `product_stock`       INT(11)       NOT NULL COMMENT '库存',
    `product_description` VARCHAR(64)            DEFAULT '无描述' NOT NULL COMMENT '商品描述',
    `product_icon`        VARCHAR(512)           DEFAULT '无图' NOT NULL COMMENT '小图',
    `product_status`      TINYINT(3)             DEFAULT '0' COMMENT '商品状态,0正常1下架',
    `category_type`       INT(11)       NOT NULL COMMENT '类目编号',
    `create_time`         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `product_info`(product_id, product_name, product_price, product_stock, product_status,category_type)
VALUES (157875196366160022, '皮蛋瘦肉粥', 0.01, 39, 0,1);

INSERT INTO `product_info`(product_id, product_name, product_price, product_stock, product_status,category_type)
VALUES (157875227953464068, '慕斯蛋糕', 10.90, 200, 1,1);

INSERT INTO `product_info`(product_id, product_name, product_price, product_stock, product_status,category_type)
VALUES (164103465734242707, '蜜汁鸡翅', 0.02, 982, 0,1);