package com.java.onlinevideo.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author changchen
 * @site
 * @company
 * @create 2021-03-18 14:58
 */
@Component
public class BaseCache {


    private Cache<String,Object> tenMinuteCache = CacheBuilder.newBuilder()
            //初始容量,后续扩容
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数，即同时几个线程写
            .concurrencyLevel(5)
            //缓存过期时间,写入后10分钟过期
            .expireAfterWrite(600, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()
            .build();

    public Cache<String, Object> getTenMinuteCache() {
        return tenMinuteCache;
    }

    public void setTenMinuteCache(Cache<String, Object> tenMinuteCache) {
        this.tenMinuteCache = tenMinuteCache;
    }
}
