package com.github.pq.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author xiaoniu 2019/12/7.
 * 冒泡排序
 */
public class BubbleSort {
    /**
     * 冒泡排序
     * @param array
     */
    public void sort(int[] array ){
        int len = array.length;
        if( len <= 1 ){
            return;
        }
        for(int i = 0; i < len ; i++) {
            boolean flag = false;
            for(int j=0; j < len - i -1 ; j++) {
                //大于 就交换
                if(array[j] > array[j+1]) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    flag = true; //表示有数据交换
                }
            }
            // 没有数据交换，提前退出
            if(!flag) {
                break;
            }
        }
    }

    @Test
    public void test1() {
        int[] array = {6,3,4,2,5,8,9,8};
        for(int i=0;i<array.length;i++) {
         System.out.print(array[i]);
        }
        sort(array);
        System.out.println("----------");
        for(int i=0;i<array.length;i++) {
            System.out.print(array[i]);
        }
    }

    @Test
    public void test5(){
        int i = 0;
        int[] arr = new int[3];
        for(;i<=3;i++){
            arr[i] = 0;
            System.out.println("hello world");
        }
    }



}
