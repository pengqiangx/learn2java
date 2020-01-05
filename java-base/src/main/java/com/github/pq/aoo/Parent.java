package com.github.pq.aoo;

import org.junit.Test;

/**
 * @author xiaoniu 2019/4/28.
 */
public class Parent implements interTwo,interOne {

    public int x;

    public Parent(int x) {
        System.out.println("hello parent constructor");
    }
    public int add(int x,int y){
        return x+y;
    }

    public static void hello(){
        System.out.println("parent");

    }


}
