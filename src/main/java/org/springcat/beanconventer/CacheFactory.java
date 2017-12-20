package org.springcat.beanconventer;

/**
 * Created by springcat on 2017/12/19.
 */
public interface CacheFactory {

    void put(Object key,Object value);

    <T> T get(Object key);
}
