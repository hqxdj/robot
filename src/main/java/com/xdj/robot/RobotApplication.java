package com.xdj.robot;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "com.xdj.robot.mapper")
@NacosConfigurationProperties(dataId = "xdjrobot", autoRefreshed = true)
public class RobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotApplication.class, args);
    }

}
