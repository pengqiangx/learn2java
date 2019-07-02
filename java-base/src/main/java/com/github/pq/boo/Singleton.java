package com.github.pq.boo;

import org.junit.Test;

public class Singleton {

    Person person ;

    static Singleton instance;

    public Singleton()  {
        //等待一下
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        person = new Person("张三",11);

        System.out.println(Thread.currentThread().getName() + "..初始化完毕....");
    }

    public Person getPerson() {
        return person;
    }

    static Singleton getInstance()  {
        if (instance == null) {
            synchronized(Singleton.class) {
                System.out.println(Thread.currentThread().getName()+"..synchronized....");
                if (instance == null) {
                    System.out.println(Thread.currentThread().getName()+"..new instance....");
                    instance = new Singleton();
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + "..get instance....");
        return instance;
    }

}
