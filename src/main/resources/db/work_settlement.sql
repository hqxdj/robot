CREATE TABLE `work_settlement`
(
    `id` int NOT NULL AUTO_INCREMENT,
    `user_name`         varchar(45)   DEFAULT NULL COMMENT '被结算人',
    `phone`             varchar(45)   DEFAULT NULL COMMENT '电话',
    `settlement_date`   date          DEFAULT NULL COMMENT '结算日期',
    `settlement_amount` decimal(8, 2) DEFAULT NULL COMMENT '结算金额',
    `settlement_bank`   varchar(45)   DEFAULT NULL COMMENT '结算银行',
    `settlement_no`     varchar(45)   DEFAULT NULL COMMENT '结算卡号',
    `settlement_user`   varchar(45)   DEFAULT NULL COMMENT '结算人（包工头）',
    `settlement_phone`  varchar(45)   DEFAULT NULL COMMENT '结算人电话',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='工时结算表'