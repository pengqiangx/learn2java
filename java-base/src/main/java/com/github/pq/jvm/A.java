package com.github.pq.jvm;

/**
 * @author xiaoniu 2019/7/21.
 */
public final class A implements B {

    private int m;

    @Override
    public int inc(){
        return m+1;
    }
}
