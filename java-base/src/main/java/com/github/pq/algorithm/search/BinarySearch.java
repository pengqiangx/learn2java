package com.github.pq.algorithm.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoniu 2019/4/22.
 * 折半查找
 */
public class BinarySearch {

    private static int count1 = 0;
    private static int count2 = 0;
    private static int count3 = 0;

    private static int[] staticArray ={1,16,24,35,47,59,62,73,77,84,88,99};
    private static int[] noBalanceArray ={1,22,333,4444,55555,666666,7777777,88888888,999999999};

    private int search1(int[] array , int key) {

        int low = 0;

        int high = array.length;

        while(low <= high) {
            count1++;
            int mid =   ( high + low ) / 2 ;
            if(key < array[mid]) {
                //最高位下标 调整到 比中位下标 小一位
                high = mid -1;
            }else if(key > array[mid]) {
                //最低位下标 调整到 比中位下标 大一位
                low = mid + 1;
            } if(key == array[mid]){
                return mid;
            }
        }
        return -1;
    }


    private int search2(int[] array , int key) {

        int low = 0;

        int high = array.length-1;

        count2 = 0;
        while(low <= high) {
            count2++;
            if(key < array[low] || key > array[high]){
                return -1;
            }
            //计算折半比例
            float rate = (float)(key-array[low]) / (float) (array[high]-array[low]);
            //mid = low + k(high-low)
            int mid = (int) (low + rate*(high-low));

            if(key < array[mid]) {
                //最高位下标 调整到 比中位下标 小一位
                high = mid -1;
            }else if(key > array[mid]) {
                //最低位下标 调整到 比中位下标 大一位
                low = mid + 1;
            } if(key == array[mid]){
                return mid;
            }
        }
        return -1;
    }
    //斐波那契数列
    private static int fibonacci(int k){
        if(k== 0){
           return 0;
        }else if( k == 1){
            return 1;
        }else if(k > 1){
            return fibonacci(k-1) + fibonacci(k-2);
        }else {
            return -1;
        }
    }
    private static List obtainFibonacciData(int len){
        ArrayList<Integer> fiboArr = new ArrayList<Integer>();
        for(int i =0; i < len;i++){
            int fibo = fibonacci(i);
            fiboArr.add(fibo);
        }
        return fiboArr;
    }

    private int search3(int[] array , int key ) {
        int low = 0;
        int high = array.length -1;
        List fiboArr = obtainFibonacciData(high);
        System.out.println("斐波那契数列为:"+fiboArr.toString());
        return 0;
    }

    @Test
    public void test1(){
        //System.out.println("下标位置:"+search1(staticArray,3)+",执行查找次数："+count1);
        System.out.println("下标位置:"+search2(staticArray,999)+",执行查找次数："+count2);
        System.out.println("下标位置:"+search2(staticArray,0)+",执行查找次数："+count2);
        System.out.println("下标位置:"+search2(staticArray,3)+",执行查找次数："+count2);
        System.out.println("下标位置:"+search2(staticArray,97)+",执行查找次数："+count2);
        System.out.println("下标位置:"+search2(staticArray,-10)+",执行查找次数："+count2);
        System.out.println("下标位置:"+search2(staticArray,88)+",执行查找次数："+count2);
        System.out.println("下标位置:"+search2(staticArray,59)+",执行查找次数："+count2);
    }

    @Test
    public void test2(){
        System.out.println("下标位置:"+search1(noBalanceArray,88888888)+",执行查找次数："+count1);
        System.out.println("下标位置:"+search2(noBalanceArray,88888888)+",执行查找次数："+count2);
    }
}
