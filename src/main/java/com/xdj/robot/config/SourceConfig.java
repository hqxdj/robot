package com.xdj.robot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class SourceConfig {

    @Resource
    private TaskConfig taskConfig;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(taskConfig.getCoreSize());
        threadPoolTaskExecutor.setMaxPoolSize(taskConfig.getMaxSize());
        threadPoolTaskExecutor.setQueueCapacity(taskConfig.getQueueCapacity());
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }

}
