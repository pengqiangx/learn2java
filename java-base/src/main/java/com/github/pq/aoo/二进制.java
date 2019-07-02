package com.github.pq.aoo;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiaoniu 2019/4/24.
 */
public class 二进制 {
    @Test
    public void test1(){
        // 20
        System.out.println(10 << 1);

        // -20
        System.out.println(-10 << 1);


        // 5
        System.out.println(10 >> 1);

        // -5
        System.out.println(-10 >> 1);

        // 5
        System.out.println(10 >>> 1);

        // 2147483643
        System.out.println(-10 >>> 1);

        System.out.println(Integer.toBinaryString(-10));
    }

    @Test
    public void test2() {
        int b = -1;
        System.out.println(Integer.toBinaryString(b & 0xFF));
        //Assert.assertNotEquals(Integer.toBinaryString(b & 0xFF),"10000001");
        //Assert.assertEquals(Integer.toBinaryString(b),"11111111");
    }
}
