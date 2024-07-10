package com.wkw2.cache.core.support.expire;

import com.wkw2.cache.api.ICache;
import com.wkw2.cache.api.ICacheExpire;
import com.wkw2.cache.core.core.Cache;
import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class CacheExpire<K,V> implements ICacheExpire<K,V> {
    /**
     * 单次清空的数量限制
     */
    private static final int LIMIT = 100;
    /**
     * 需要过期的key列表 k key , v 过期时间
     */
    private final Map<K,Long> expireMap = new ConcurrentHashMap<>();
    /**
     * 缓存对象
     */
    private ICache<K,V> cache;

    public CacheExpire(ICache<K, V> cache) {
        this.cache = cache;
    }

    @Override
    public void expire(K key, Long timeInMills) {
        this.expireAt(key, System.currentTimeMillis() + timeInMills);
    }

    @Override
    public void expireAt(K key, Long timeInMills) {
        expireMap.put(key, timeInMills);
    }

    /**
     * 移除过期key
     * @param key
     * @param timeInMills
     */
    public void expireKey(final K key,final Long timeInMills){
        if(timeInMills != null && timeInMills <= System.currentTimeMillis()){
            expireMap.remove(key);
            // 再移除缓存，后续可以通过惰性删除做补偿
            cache.remove(key);
        }
    }

    @Override
    public void clear() {
        expireMap.clear();
    }

    @Override
    public void clear(K key) {
        expireMap.remove(key);
    }

    /**
     * 刷新过期时间
     * @param keyList
     */
    @Override
    public void refreshExpire(Collection<K> keyList) {
        if(keyList == null || keyList.isEmpty()){
            return;
        }
        keyList.forEach(key -> {
            Long timeInMills = expireMap.get(key);
                this.expireKey(key, timeInMills);
        });
    }
}
