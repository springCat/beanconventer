package org.springcat.beanconventer;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Created by springcat on 2017/12/19.
 */
public class GuavaCache implements CacheFactory{

    private Cache cache = CacheBuilder.newBuilder().build();

    public void put(Object key, Object value) {
        cache.put(key,value);
    }

    public <T> T get(Object key) {
        return (T) cache.getIfPresent(key);

    }
}
