package com.github.pq.boo;

/**
 * @author xiaoniu 2019/5/1.
 */
class X {
    // 修饰非静态方法
    synchronized void foo() {
        // 临界区
    }
    // 修饰静态方法
    synchronized static void bar() {
        // 临界区
    }
    // 修饰代码块
    Object obj = new Object();

    void baz() {
        synchronized(obj) {
            // 临界区
        }
    }
}



