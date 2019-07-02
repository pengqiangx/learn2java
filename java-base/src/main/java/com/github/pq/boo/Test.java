package com.github.pq.boo;

public class Test {

    private volatile int count = 0;

    private void add() {
        int idx = 0;
        while(idx++ < 10000) {
            count += 1;
        }
    }

    public static int calc() throws Exception {
        final Test test = new Test();
        Thread th1 = new Thread(()-> {
            test.add();
        });
        Thread th2 = new Thread(()->{
            test.add();
        });

        th1.start();
        th2.start();
        th1.join();
        th2.join();
        return test.count;
    }

    public static void main(String[] args) throws Exception {
        //long c =calc();
        //System.out.println(c);
        int s = "1100000,120000,370000,220000,310000,210000,420000,140000,520000".indexOf("230000") ;
        System.out.println(s);

    }
}








