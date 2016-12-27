package com.gloresoft.caching;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        GuavaCache conversions = new GuavaCache("conversions", CacheBuilder.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .maximumSize(500)
                .build());
        simpleCacheManager.setCaches(Arrays.asList(conversions));
        return simpleCacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}
