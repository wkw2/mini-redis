package com.wkw2.cache.core.core;

import com.wkw2.cache.api.*;
import com.wkw2.cache.core.support.evivt.CacheEvictContext;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class Cache<K, V> implements ICache<K, V> {

    /**
     * 缓存map信息
     */
    private Map<K, V> map;
    /**
     * 缓存大小
     */
    private int sizeLimit;

    /**
     * 启动时持久化策略
     */
    private ICacheLoad<K, V> load;
    /**
     * 缓存过期策略
     */
    private ICacheExpire<K, V> expire;
    /**
     * 内存淘汰策略
     */
    private ICacheEvict<K, V> evict;
    /**
     * 删除监听类
     */
    private List<ICacheRemoveListener<K, V>> removeListeners;

    /**
     * 慢日志监听类
     */
    private List<ICacheSlowListener> slowListeners;

    @Override
    public ICache<K, V> expire(K key, long timeInMills) {
        return null;
    }

    @Override
    public ICache<K, V> expireAt(K key, long timeInMills) {
        long ct = System.currentTimeMillis() + timeInMills;

        return null;
    }

    @Override
    public ICacheExpire<K, V> expire() {
        return this.expire;
    }


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        //1. 尝试驱除
        CacheEvictContext<K, V> context = new CacheEvictContext<>();
        context.cache(this).key(key).size(sizeLimit);

        // 执行淘汰策略
        boolean evicted = this.evict.evict(context);


        //2. 判断驱除后的信息
        if (map.size() >= sizeLimit) {
            throw new RuntimeException("当前队列已满，数据添加失败！");
        }
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
