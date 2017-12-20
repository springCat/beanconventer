package org.springcat.beanconventer;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeanConventer
{

    private CacheFactory cache;

    public BeanConventer(CacheFactory cache) {
        this.cache = cache;
    }

    public void register(Class sourceClass, Class targetClass){
        registerCopy(sourceClass, targetClass);
        registerCopy(targetClass, sourceClass);
        registerBean(sourceClass);
        registerBean(targetClass);

    }

    public void registerCopy(Class sourceClass, Class targetClass){
        BeanCopier beanCopier = BeanCopier.create(sourceClass, targetClass, false);

        BeanTransforKey key = new BeanTransforKey(sourceClass, targetClass);
        cache.put(key,beanCopier);
    }

    public void registerBean(Class beanClass){
        ConstructorAccess constructor = ConstructorAccess.get(beanClass);
        cache.put(beanClass,constructor);
    }
    
    public void copy(Object source, Object target)
    {
        Class sourceClass = source.getClass();
        Class targetClass = target.getClass();
        BeanTransforKey key = new BeanTransforKey(sourceClass, targetClass);
        
        BeanCopier copier = cache.get(key);
        if(copier != null){
            copier.copy(source, target, null);
        }
    }

    public <T> List<T> copyList(List sourceList, Class<T> targetClass)
    {
        if(sourceList == null && sourceList.size() == 0)
        {
            return null;
        }


        List<T> list = new ArrayList<T>(sourceList.size());
        ConstructorAccess<T> constructor = cache.get(targetClass);

        if(constructor == null){
            return null;
        }

        for (int i = 0; i < sourceList.size(); i++)
        {
            T bean = constructor.newInstance(); 
            copy(sourceList.get(i), bean);
            list.add(bean);
        }
        
        return list;
    }
    
    public BeanMap toMap(Object source)
    {
        return BeanMap.create(source);
    }
    
    public <T> T toBean(Map map,Class<T> targetClass)
    {
        ConstructorAccess<T> constructor = cache.get(targetClass);
         T bean = constructor.newInstance();     
         BeanMap beanMap = BeanMap.create(bean); 
        beanMap.putAll(map);
        return bean;
    }
    
}
