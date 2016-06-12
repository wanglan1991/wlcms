package com.ekt.cms.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * @author sxjun
 * @time 2015/8/12 16:09
 */
public class WTCacheManager implements org.apache.shiro.cache.CacheManager{

    private Cache cache;

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return cache;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }
}
