package com.gloresoft.caching;

import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${cache.expiry.time}")
    private int expiryTime;

    @Value("${cache.max.records}")
    private int maximumSize;

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        GuavaCache conversions = new GuavaCache("conversions", CacheBuilder.newBuilder()
                .expireAfterAccess(expiryTime, TimeUnit.MINUTES)
                .maximumSize(maximumSize)
                .build());
        simpleCacheManager.setCaches(Arrays.asList(conversions));
        return simpleCacheManager;
    }
}
