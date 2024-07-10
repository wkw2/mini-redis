package com.wkw2.cache.test;

import com.wkw2.cache.core.bs.CacheBs;
import com.wkw2.cache.core.core.Cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


@Slf4j
public class CacheTest {

    @Test
    public void test() throws InterruptedException {

        Cache<String, String> cache = CacheBs.<String, String>newInstance().sizeLimit(3).build();
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.expire("1", 100);
        cache.expire("2", 200);
        cache.expire("3", 300);
        TimeUnit.MILLISECONDS.sleep(150);
        log.info("{}", cache.get("1"));
        log.info("{}", cache.get("2"));
        log.info("{}", cache.get("3"));
    }
}
