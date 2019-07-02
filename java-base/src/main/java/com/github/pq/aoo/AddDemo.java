package com.github.pq.aoo;

import org.junit.Test;

/**
 * @author xiaoniu 2019/6/28.
 */
public class AddDemo {

    @Test
    public void test(){
        int i = 0;
        //先将i的原始值（0）赋值给变量j1（0），然后i变量的值加1
        int j= i++;
        System.out.println("i="+i+",j="+j);
    }
    @Test
    public void test1(){
        int i = 0;
        //先将i变量的值加1，然后将i的当前值（1）赋值给变量j（1）
        int j = ++i;
        System.out.println("i="+i+",j="+j);
    }
}
