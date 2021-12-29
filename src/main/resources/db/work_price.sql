CREATE TABLE `work_price`
(
    `id` int NOT NULL AUTO_INCREMENT,
    `user_name`    varchar(45) DEFAULT NULL COMMENT '姓名',
    `phone`        varchar(45) DEFAULT NULL COMMENT '电话',
    `address`      varchar(45) DEFAULT NULL COMMENT '包工头地址',
    `price`        varchar(45) DEFAULT NULL COMMENT '工时单价',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='工时报价表'