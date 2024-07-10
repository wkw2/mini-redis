package com.wkw2.cache.test;

import com.wkw2.cache.core.bs.CacheBs;
import com.wkw2.cache.core.core.Cache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


@Slf4j
public class CacheTest {

    @Test
    public void test() {

        Cache<String, String> cache = CacheBs.<String, String>newInstance().sizeLimit(2).build();
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");

    }
}
