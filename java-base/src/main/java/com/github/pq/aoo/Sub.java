package com.github.pq.aoo;

import org.junit.Test;

/**
 * @author xiaoniu 2019/4/28.
 */
public class Sub extends Parent {

    public Sub() {
        super(0);
        System.out.println("hello sub constructor");
    }

    public int go() {
        int m = 0;
        int n = 1;
        return add(m, n);
    }

}
