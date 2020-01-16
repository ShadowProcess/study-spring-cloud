TRUNCATE TABLE `product_category`;
CREATE TABLE `product_category`(
    `category_id`         INT(11)   NOT NULL AUTO_INCREMENT,
    `category_name`       VARCHAR(64)   NOT NULL COMMENT '类目名字',
    `category_type`       INT(11)       NOT NULL COMMENT '类目编号',
    `create_time`         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`category_id`),
    UNIQUE KEY `uqe_category_type`(`category_type`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `product_category`(category_name, category_type)
VALUES('热榜',1);

INSERT INTO `product_category`(category_name, category_type)
VALUES('好吃的',22);