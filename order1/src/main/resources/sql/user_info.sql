-- 用户
CREATE TABLE `user_info`(
    `id` VARCHAR(32) NOT NULL,
    `username` VARCHAR(32) DEFAULT '',
    `password` VARCHAR(32) DEFAULT '',
    `openid` VARCHAR(64) DEFAULT '' COMMENT '微信openid',
    `role` TINYINT(1) NOT NULL COMMENT '1买家2卖家',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY(`id`)
);