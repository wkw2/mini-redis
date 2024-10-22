package com.wkw2.cache.core.support.expire;

import com.wkw2.cache.api.ICache;
import com.wkw2.cache.api.ICacheExpire;
import com.wkw2.cache.core.core.Cache;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CacheExpire<K,V> implements ICacheExpire<K,V> {
    /**
     * 单次清空的数量限制
     */
    private static final int LIMIT = 100;
    /**
     * 需要过期的key列表 k key , v 过期时间
     */
    private final Map<K,Long> expireMap = new HashMap<>();
    /**
     * 缓存对象
     */
    private ICache<K,V> cache;


    @Override
    public void expire(K key, Long timeInMills) {
        this.expireAt(key, System.currentTimeMillis() + timeInMills);
    }

    @Override
    public void expireAt(K key, Long timeInMills) {
        expireMap.put(key, timeInMills);
    }

    @Override
    public void clear() {
        expireMap.clear();
    }

    @Override
    public void clear(K key) {
        expireMap.remove(key);
    }
}
