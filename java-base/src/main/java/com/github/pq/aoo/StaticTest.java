package com.github.pq.aoo;

/**
 * @author xiaoniu 2019/2/27.
 */
public class StaticTest {

    public  String method1(){
        return "hello one";
    }


    public static String method2(){
        return "hello two";
    }

    public static void main(String[] args){
        StaticTest test = new StaticTest();
        test.method1();
        // test.method2();  --- error
        StaticTest.method2();
    }
}
