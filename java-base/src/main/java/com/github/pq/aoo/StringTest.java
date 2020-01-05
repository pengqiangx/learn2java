package com.github.pq.aoo;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author xiaoniu 2019/7/2.
 */
public class StringTest {

    @Test
    public void test1(){
        String a = "123";
        String b = "123";
        System.out.println(a==b);
    }

    @Test
    public void test2() {
        String c = "123";
        String d = new String("123");
        System.out.println(c==d);
    }

    @Test
    public void test3() {
        String e = "123";
        String f = new String("123");
        String g = f.intern();
        System.out.println(g==e);
        System.out.println(g==f);
    }

    @Test
    public void test4() {
        String h = "123";
        String i = "12"+"3";
        System.out.println(h==i);
    }


    public static void main(String[] args){
        String j = "123";
        String k = "12";
        String l = "3";
        System.out.println(j==k+l);
    }

    @Test
    public void test6(){
        String c = "123".intern();
        String d = "123";
        System.out.println(c ==d);
    }

    @Test
    public void test7(){
        String a = "hello";
        for(int i = 0;i<1000;i++){
            a=a+i ;
        }
        System.out.println(a);
    }
    @Test
    public void test8(){
        String a = "hello";
        for(int i = 0;i<1000;i++){
            //a=a+i --> 编译器优化--> StringBuilder
            a = (new StringBuilder(String.valueOf(a))).append(i).toString();
        }
        System.out.println(a);
    }

    @Test
    public void test9(){
        StringBuilder a = new StringBuilder("hello");
        for(int i = 0;i<1000;i++){
            a.append(i);
        }
        System.out.println(a);
    }

    @Test
    public void test10() {
         int b[][] ={{1},{2,2},{2,2,2}};
         int sum = 1;
         for(int i=0;i<b.length;i++){
             for(int j=0 ;j<b[i].length;j++){
                 int s = b[i][j];
                 sum=sum * s;
             }
         }
         System.out.println("sum="+sum);
    }



    @Test
    public void test11(){
        int a[][] = {{1,2,3}};
        //把{}中的数据依次赋给a数组各元素（ 1行 ）
        Assert.assertSame(1,a[0][0]);
        Assert.assertSame(2,a[0][1]);
        Assert.assertSame(3,a[0][2]);
    }
    @Test
    public void test12(){
        //分行进行初始化
        int a[][] = {{1,2,3},{4,5,6}};
        //在{}内部再用{}把各行分开，第一对{}中的初值1，2，3是0行的3个元素的初值。第二对{}中的初值4，5，6是1行的3个元素的初值
         Assert.assertSame(1,a[0][0]);
         Assert.assertSame(2,a[0][1]);
         Assert.assertSame(3,a[0][2]);
         Assert.assertSame(4,a[1][0]);
         Assert.assertSame(5,a[1][1]);
         Assert.assertSame(6,a[1][2]);
    }
    @Test
    public void test13(){
        //分行进行初始化
        int a[][] = {{1},{2,2}};
        Assert.assertSame(1,a[0][0]);
        //System.out.println(a[0][1]); --> java.lang.ArrayIndexOutOfBoundsException: 1
        Assert.assertSame(2,a[1][0]);
        Assert.assertSame(2,a[1][1]);
    }

    @Test
    public void test14(){
       int a[][] = new int[2][2];
        for(int i=0;i<a.length;i++){
            for(int j=0 ;j<a[i].length;j++){
                //都是0
                Assert.assertSame(0,a[i][j]);
            }
        }
    }
    @Test(expected = NullPointerException.class)
    public void test15(){
        int a[][] = new int[2][];
        for(int i=0;i<a.length;i++){
            for(int j=0 ;j<a[i].length;j++){
                System.out.println(a[i][j]);
            }
        }
    }

    @Test
    public void test16(){
        int a[][] = new int[2][];
        for(int i=0;i<a.length;i++){
            //给2维分配引用空间
            a[i] = new int[2];
            for(int j=0 ;j<a[i].length;j++){
                System.out.println(a[i][j]);
            }
        }
    }

    @Test
    public void test17(){
        int a = -3;
        int b = -9 ;

        System.out.println(a%b);
    }
}
