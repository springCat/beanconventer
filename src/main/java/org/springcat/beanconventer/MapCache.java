package org.springcat.beanconventer;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by springcat on 2017/12/19.
 */
public class MapCache implements CacheFactory{

    private Map map = Maps.newHashMap();

    public void put(Object key, Object value) {
        map.put(key,value);
    }

    public <T> T get(Object key) {
        return (T) map.get(key);

    }
}
