package com.github.pq.boo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaoniu 2019/4/30.
 */
public class VolatileExample {

    int x = 0;

    boolean v = false;

    public void writer() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = 42;
        v = true;

    }

    public void reader() {
        if (v == true) {
            // 这里 x 会是多少呢？
            System.out.println(x);
        }
    }


}


