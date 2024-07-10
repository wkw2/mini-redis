//package com.wkw2.cache.core.evivt;
//
//import com.wkw2.cache.api.ICacheEvict;
//import com.wkw2.cache.api.ICacheEvictContext;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//public class LruICacheEvict<K,V> extends LinkedHashMap<K,V> implements ICacheEvict<K, V> {
//
//
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
//        return size()>max;
//    }
//
//    @Override
//    public boolean evict(ICacheEvictContext<K, V> context) {
//        if(size()>=max){
//            return true;
//        }
//        return false;
//    }
//}
