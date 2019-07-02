package com.github.pq.boo;

/**
 * @author xiaoniu 2019/3/18.
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
