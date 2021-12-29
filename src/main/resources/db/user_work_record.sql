CREATE TABLE `user_work_record`
(
    `id` int          NOT NULL AUTO_INCREMENT,
    `user_name`          varchar(45)  NOT NULL COMMENT '''姓名''',
    `phone`              varchar(45)  NOT NULL COMMENT '''电话''',
    `work_date`          date         NOT NULL COMMENT '''工作日期''',
    `address`            varchar(127) NOT NULL COMMENT '''地点''',
    `work_time`          varchar(45)  NOT NULL COMMENT '''工作时长''',
    `out_work_time`      varchar(45) DEFAULT NULL COMMENT '''加班时长''',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='人员工时记录表'