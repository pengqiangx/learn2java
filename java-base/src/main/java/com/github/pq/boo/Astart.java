package com.github.pq.boo;

import org.junit.Test;

/**
 * @author xiaoniu 2019/4/30.
 */
public class Astart {

    int var ;

    public void hello() throws InterruptedException {

        Thread B = new Thread(()->{
            // 此处对共享变量 var 修改
            System.out.println(Thread.currentThread().getName()+":"+ var); //33
            var = 66;
        });
        // 例如此处对共享变量修改， 则这个修改结果对线程 B 可见
        var = 33;
        // 主线程启动子线程
        B.start();
        B.join();
        // 子线程所有对共享变量的修改,在主线程调用 B.join() 之后皆可见
        System.out.println(Thread.currentThread().getName()+":"+ var); // 66

    }

    @Test
    public void test1() throws InterruptedException {
        Astart s = new Astart();
        s.hello();
    }
}
