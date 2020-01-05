package com.github.pq.boo;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author xiaoniu 2019/3/18.
 */
public class Boo {

    @Test
    public void volatileExample() throws InterruptedException {
        VolatileExample v = new VolatileExample();
        Thread th1 = new Thread(()->{

            v.writer();

        });
        Thread th2 = new Thread(()->{
            v.reader();

        });

        th1.start();
        th2.start();
        th1.join();
        th2.join();
    }

    @Test
    public void test8iface() {
        //formula对象以匿名对象的形式实现了Formula接口
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        assertEquals(formula.calculate(100),100.0d); // 100.0
        assertEquals(formula.sqrt(16),4.0d); // 4.0

    }

    @Test
    public void test1() throws InterruptedException {

        Thread th1 = new Thread(()->{

            Singleton s =  Singleton.getInstance();
            System.out.println(Thread.currentThread().getName()+":"+s.getPerson().getName());

        });
        Thread th2 = new Thread(()->{

            Singleton s =  Singleton.getInstance();
            System.out.println(Thread.currentThread().getName()+":"+s.getPerson().getName());



        });

        Thread th3 = new Thread(()->{

            Singleton s =  Singleton.getInstance();
            System.out.println(Thread.currentThread().getName()+":"+s.getPerson().getName());



        });


        th1.start();
        th2.start();
        th3.start();
        System.out.println(  "..........");
        th1.join();
        th2.join();
        th3.join();


    }


}
