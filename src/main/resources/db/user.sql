CREATE TABLE `user`
(
    `id`    int         NOT NULL AUTO_INCREMENT,
    `user_name` varchar(45) NOT NULL,
    `sex`       varchar(45) DEFAULT NULL,
    `phone`     varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表'