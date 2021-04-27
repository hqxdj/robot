package com.xdj.robot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.task.execution.pool")
public class TaskConfig {

    private int coreSize;

    private int maxSize;

    private int queueCapacity;

}
