package com.github.pq.aoo;

import org.junit.Test;

/**
 * @author xiaoniu 2019/4/28.
 */
public class TestOne {
    @Test
    public void test1() {
        Sub sub = new Sub();
        sub.go();
    }

    @Test
    public void test2() {
        Parent sub = new Sub();
        sub.add(1,4);
    }
}
