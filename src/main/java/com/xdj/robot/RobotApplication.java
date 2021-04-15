package com.xdj.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RobotApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RobotApplication.class, args);
    }

}
