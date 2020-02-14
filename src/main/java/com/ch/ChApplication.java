package com.ch;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/** 使用缓存的步骤：
*       1.开启缓存注解@EnableCaching
*       2.在需要缓存的方法上加上注解
*           @Cacheable(将方法的返回值放入缓存中)
*           @CachePut
*           @CacheEvict
*           @Caching
*/
@EnableCaching
@SpringBootApplication
@Slf4j
@MapperScan("com.ch.mapper")
public class ChApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChApplication.class, args);

        log.info("项目启动成功...");
    }

}
