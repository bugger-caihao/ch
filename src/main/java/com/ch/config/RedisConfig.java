package com.ch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: RedisConfig
 * @Description: redis配置类
 * @Author: caihao
 * @Date: 2019/7/27 20:56
 */
@Configuration
//@EnableCaching  //  这里的@EnableCaching项目的主启动类中已经配置了，所以这里不用这个注解可以开启缓存
public class RedisConfig {

    //  默认的缓存过期时间
    @Value("${cache.default.expire-time}")
    private int defaultExpireTime;

    //  cacheName为“test”的缓存过期时间
    @Value("${cache.test.expire-time}")
    private int testExpireTime;
    @Value("${cache.test.name}")
    private String testCacheName;

    //  cacheName为“user”的缓存过期时间
    @Value("${cache.user.expire-time}")
    private int userExpireTime;
    @Value("${cache.user.name}")
    private String userCacheName;

    //缓存管理器
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory lettuceConnectionFactory) {
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
        // 设置缓存管理器管理的缓存的默认过期时间
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(defaultExpireTime))
                // 设置 key为string序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 设置value为json序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                // 不缓存空值
                .disableCachingNullValues();

        Set<String> cacheNames = new HashSet<>();
        cacheNames.add(testCacheName);
        cacheNames.add(userCacheName);

        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        //  设置缓存管理器管理的缓存的默认过期时间（这里单指的是项目中已经配置的cacheName为“test”和“user”的缓存）
        configMap.put(testCacheName, defaultCacheConfig.entryTtl(Duration.ofSeconds(testExpireTime)));
        configMap.put(userCacheName,defaultCacheConfig.entryTtl(Duration.ofSeconds(userExpireTime)));

        RedisCacheManager cacheManager = RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }





}
