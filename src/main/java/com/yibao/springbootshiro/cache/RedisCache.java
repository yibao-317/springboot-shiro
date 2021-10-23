package com.yibao.springbootshiro.cache;

import com.yibao.springbootshiro.util.ApplicationContextUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author liyi
 * @create 2021 -10 -19 -0:12
 */
public class RedisCache<k, v> implements Cache<k, v> { // 自定义 Redis 缓存实现
    // 利用构造器，获取cache
    private String cacheName;

    public RedisCache() {
    }

    public RedisCache(String cacheName) {
        this.cacheName = cacheName;
    }

    /**
     * 方法：封装私有方法，通过自定义工厂获取bean
     *
     * @return
     */
    private RedisTemplate getRedisTemplate() {
        // 使用自定义工厂获取bean
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        // 序列化外面的 K
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 序列化里面的 k
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }


    @Override
    public v get(k k) throws CacheException {
        System.out.println("get k: " + k);
//        v v = (v) getRedisTemplate().opsForValue().get(k.toString());

        // <K,<k,v>> 的获取
        v v = (v) getRedisTemplate().opsForHash().get(this.cacheName, k.toString());
        return v;
    }

    @Override
    public v put(k k, v v) throws CacheException {
        System.out.println("put k： " + k);
        System.out.println("put v： " + v);
//        getRedisTemplate().opsForValue().set(k.toString(), v);

        // 可以将 cacheName使用上，<K,<k,v>> 结构
        getRedisTemplate().opsForHash().put(this.cacheName, k.toString(), v);
        return null;
    }

    @Override
    public v remove(k k) throws CacheException {
        // 移除
        v value = (v) getRedisTemplate().opsForHash().delete(this.cacheName, k.toString());
        return value;
    }

    @Override
    public void clear() throws CacheException {
        // 清除
        getRedisTemplate().delete(this.cacheName);
    }

    @Override
    public int size() {
        // 数量
        Long size = getRedisTemplate().opsForHash().size(this.cacheName);
        return size.intValue();
    }

    @Override
    public Set<k> keys() {
        // 获取 keys
        Set keys = getRedisTemplate().opsForHash().keys(this.cacheName);
        return keys;
    }

    @Override
    public Collection<v> values() {
        // 获取 values
        List values = getRedisTemplate().opsForHash().values(this.cacheName);
        return values;
    }
}
