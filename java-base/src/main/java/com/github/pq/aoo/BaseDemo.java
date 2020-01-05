package com.github.pq.aoo;

import org.junit.Test;

import static java.lang.Math.floor;

/**
 * @author xiaoniu 2019/7/11.
 */
public class BaseDemo {
    @Test
    public void test1(){
        String user_id = "18519200819";
        System.out.println(Integer.parseInt(String.valueOf(user_id).substring(user_id.length()-4)) % 2);
    }

    @Test
    public void test2(){
        String user_id = "18519200819";
        int s = (int)(Math.floor(Integer.parseInt(String.valueOf(user_id).substring(user_id.length()-4))/100)%100);
        System.out.println(s);
    }
}
