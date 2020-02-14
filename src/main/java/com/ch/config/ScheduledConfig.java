package com.ch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @ClassName: ScheduledConfig
 * @Description: TODO
 * @Author: caihao
 * @Date: 2020/2/14 19:45
 */
@Configuration
@EnableAsync
public class ScheduledConfig implements SchedulingConfigurer {

    //开启一个线程调度池，可以让多个任务并行执行
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(100));

    }

}
