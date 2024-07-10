package com.wkw2.cache.core.bs;

import com.wkw2.cache.api.*;
import com.wkw2.cache.core.core.Cache;
import com.wkw2.cache.core.support.evivt.CacheEvictFIFO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class CacheBs<K,V> {
    public CacheBs() {}
    /**
     * 创建对象实例
     * @param <K> key
     * @param <V> value
     * @return this
     * @since 0.0.2
     */
    public static <K,V> CacheBs<K,V> newInstance() {
        return new CacheBs<K,V>();
    }

    public CacheBs<K,V> sizeLimit(int sizeLimit) {
        this.sizeLimit = sizeLimit;
        return this;
    }
    public CacheBs<K,V> expire(ICacheExpire<K,V> expire) {
        this.expire = expire;
        return this;
    }
    public CacheBs<K,V> load(ICacheLoad<K,V> load) {
        this.load = load;
        return this;
    }
    public CacheBs<K,V> removeListeners(List<ICacheRemoveListener<K,V>> removeListeners) {
        this.removeListeners = removeListeners;
        return this;
    }
    public CacheBs<K,V> slowListeners(List<ICacheSlowListener> slowListeners) {
        this.slowListeners = slowListeners;
        return this;
    }


    public Cache<K,V> build() {
        Cache<K, V> cache = new Cache<>();
        cache.setMap(map);
        cache.setSizeLimit(sizeLimit);
        cache.setExpire(expire);
        cache.setLoad(load);
        cache.setRemoveListeners(removeListeners);
        cache.setSlowListeners(slowListeners);
        cache.setEvict(evict);
        return cache;
    }


    /**
     * 缓存map信息
     */
    private Map<K,V> map = new HashMap<>();
    /**
     * 缓存大小
     */
    private int sizeLimit = Integer.MAX_VALUE;
    /**
     * 启动时持久化策略
     */
    private ICacheLoad<K,V> load;
    /**
     * 缓存过期策略
     */
    private ICacheExpire<K,V> expire;
    /**
     * 缓存淘汰策略
     */
    private ICacheEvict<K,V> evict = new CacheEvictFIFO<>();

    /**
     * 删除监听类
     */
    private List<ICacheRemoveListener<K,V>> removeListeners;

    /**
     * 慢日志监听类
     */
    private List<ICacheSlowListener> slowListeners;

}
