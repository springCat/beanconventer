package org.springcat.beanconventer;

import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * Created by springcat on 2017/12/19.
 */
public class Benchmark {

    @Test
    public void beanset(){

        BeanConventer beanConventer = new BeanConventer(new MapCache());
        beanConventer.registerCopy(SourceObj.class,TargetObj.class);

        SourceObj sourceObj = new SourceObj();
        sourceObj.setId(1L);
        sourceObj.setAge(16);
        sourceObj.setSex("male");
        sourceObj.setName("springcat");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            TargetObj targetObj1 = toTargetObj(sourceObj);
        }
        long end = System.currentTimeMillis();

        System.out.println("beanset:"+ (end - start));
    }

    @Test
    public void testCglib() {

        SourceObj sourceObj = new SourceObj();
        sourceObj.setId(1L);
        sourceObj.setAge(16);
        sourceObj.setSex("male");
        sourceObj.setName("springcat");

        BeanCopier beanCopier = BeanCopier.create(SourceObj.class, TargetObj.class, false);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {

            TargetObj targetObj = new TargetObj();
            beanCopier.copy(sourceObj,targetObj,null);
        }
        long end = System.currentTimeMillis();

        System.out.println("cglib:"+ (end - start));
    }

    @Test
    public void testCopy() {


        BeanConventer beanConventer = new BeanConventer(new MapCache());
        beanConventer.registerCopy(SourceObj.class,TargetObj.class);

        SourceObj sourceObj = new SourceObj();
        sourceObj.setId(1L);
        sourceObj.setAge(16);
        sourceObj.setSex("male");
        sourceObj.setName("springcat");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            TargetObj targetObj = new TargetObj();
            beanConventer.copy(sourceObj,targetObj);
        }
        long end = System.currentTimeMillis();

        System.out.println("copy:"+ (end - start));
    }

    @Test
    public void testCopyGuava() {

        BeanConventer beanConventer = new BeanConventer(new GuavaCache());
        beanConventer.registerCopy(SourceObj.class,TargetObj.class);

        SourceObj sourceObj = new SourceObj();
        sourceObj.setId(1L);
        sourceObj.setAge(16);
        sourceObj.setSex("male");
        sourceObj.setName("springcat");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            TargetObj targetObj = new TargetObj();
            beanConventer.copy(sourceObj,targetObj);
        }
        long end = System.currentTimeMillis();

        System.out.println("copy guava:"+ (end - start));
    }

    //@Test
    public void testBeanUtils() {

        SourceObj sourceObj = new SourceObj();
        sourceObj.setId(1L);
        sourceObj.setAge(16);
        sourceObj.setSex("male");
        sourceObj.setName("springcat");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {

            TargetObj targetObj = new TargetObj();
            BeanUtils.copyProperties(sourceObj,targetObj);
        }
        long end = System.currentTimeMillis();
        System.out.println("beanutils::"+ (end - start));
    }

    private static TargetObj toTargetObj(SourceObj sourceObj) {
        if (sourceObj == null) {
            return null;
        }
        TargetObj targetObj = new TargetObj();
        targetObj.setId(sourceObj.getId());
        targetObj.setName(sourceObj.getName());
        targetObj.setSex(sourceObj.getSex());
        targetObj.setAge(sourceObj.getAge());
        return targetObj;
    }
}
