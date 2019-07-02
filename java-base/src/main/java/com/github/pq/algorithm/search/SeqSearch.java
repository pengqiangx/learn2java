package com.github.pq.algorithm.search;

import org.junit.Test;

/**
 * @author xiaoniu 2019/4/20.
 */
public class SeqSearch {
    @Test
    public void test1(){
        int[] array ={1,2,3,4};
        System.out.println(search(array,3));
    }

    private int search(int[] array,int key) {
        for(int i = 0; i < array.length; i ++){
            if(array[i] == key){
                return i;
            }
        }
        return -1;
    }

    private int search2(int[] array,int key) {
        if(array[0] == key){
           return 0;
        }
        array[0] = key;
        int i = 1;
        while (array[i] != key){
            i--;
        }
        return -1;
    }

}
