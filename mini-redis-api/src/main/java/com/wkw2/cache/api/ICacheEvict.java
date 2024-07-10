package com.wkw2.cache.api;

public interface ICacheEvict<K,V> {
    boolean evict(ICacheEvictContext<K,V> context);
}
