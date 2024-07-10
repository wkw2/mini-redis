package com.wkw2.cache.api;

import java.util.Collection;

public interface ICacheExpire<K, V> {
    void expire(K key, Long timeInMills);

    void expireAt(K key, Long timeInMills);

    void clear();

    void clear(K key);
    public void refreshExpire(Collection<K> keyList);
}
