package org.springcat.beanconventer;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by springcat on 2017/12/19.
 */
public class BeanConventerTest {

    @Test
    public void testCopy() {

        long start = System.currentTimeMillis();
        BeanConventer beanConventer = new BeanConventer(new MapCache());
        beanConventer.registerCopy(SourceObj.class,TargetObj.class);

        SourceObj sourceObj = new SourceObj();
        sourceObj.setId(1L);
        sourceObj.setAge(16);
        sourceObj.setSex("male");
        sourceObj.setName("springcat");


        for (int i = 0; i < 10000000; i++) {
            TargetObj targetObj = new TargetObj();
            beanConventer.copy(sourceObj,targetObj);
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    @Test
    public void testCopyList() {

        long start = System.currentTimeMillis();
        BeanConventer beanConventer = new BeanConventer(new MapCache());
        beanConventer.register(SourceObj.class,TargetObj.class);

        SourceObj sourceObj = new SourceObj();
        sourceObj.setId(1L);
        sourceObj.setAge(16);
        sourceObj.setSex("male");
        sourceObj.setName("springcat");

        SourceObj sourceObj1 = new SourceObj();
        sourceObj1.setId(1L);
        sourceObj1.setAge(16);
        sourceObj1.setSex("male");
        sourceObj1.setName("springcat");

        List<SourceObj> sourceObjs = Arrays.asList(sourceObj,sourceObj1);

        for (int i = 0; i < 10000000; i++) {
            List<TargetObj> targetObjs = beanConventer.copyList(sourceObjs, TargetObj.class);
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    @Test
    public void testBean2Map() {

        long start = System.currentTimeMillis();
        BeanConventer beanConventer = new BeanConventer(new MapCache());
        beanConventer.registerBean(SourceObj.class);

        SourceObj sourceObj = new SourceObj();
        sourceObj.setId(1L);
        sourceObj.setAge(16);
        sourceObj.setSex("male");
        sourceObj.setName("springcat");


        for (int i = 0; i < 10000000; i++) {
            beanConventer.toMap(sourceObj);
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }


    @Test
    public void testMap2Bean() {

        long start = System.currentTimeMillis();
        BeanConventer beanConventer = new BeanConventer(new MapCache());
        beanConventer.registerBean(SourceObj.class);

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id",1L);
        map.put("age",16);
        map.put("sex","male");
        map.put("name","springcat");


        for (int i = 0; i < 10000000; i++) {
            SourceObj sourceObj1 = beanConventer.toBean(map, SourceObj.class);
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
