package com.github.pq.boo;

import java.util.List;
import java.util.Vector;

/**
 * @author xiaoniu 2019/5/1.
 */
public class Allocator2 {

    private List<Object> als;

    // 一次性申请所有资源
    synchronized void apply(Object from, Object to){
        // 经典写法
        while(als.contains(from) ||als.contains(to)) {
            try{
                wait();
            }catch(Exception e){
                // exception
            }
        }
        als.add(from);
        als.add(to);
    }
    // 归还资源
    synchronized void free(Object from, Object to){
        als.remove(from);
        als.remove(to);
        notifyAll();
    }

    void addIfNotExist(Vector v, Object o) {
        if(!v.contains(o)) {
            v.add(o);
        }
    }
}



