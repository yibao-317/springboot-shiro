package com.yibao.springbootshiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author liyi
 * @create 2021 -10 -19 -0:03
 */
public class RedisCacheManager implements CacheManager { // 自定义shiro缓存器
    // 参数：认证或授权缓存的统一名称 authenticationCache/authorizationCache
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        // 返回自定义 redis 缓存实现
        return new RedisCache<K, V>(cacheName); // 可以将缓存名一起传过去
    }
}
