package com.xdj.robot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan(basePackages = "com.xdj.robot.mapper.auto")
@SpringBootApplication
public class RobotApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RobotApplication.class, args);
    }

}
