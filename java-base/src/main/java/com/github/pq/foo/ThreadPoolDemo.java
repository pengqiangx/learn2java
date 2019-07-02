package com.github.pq.foo;

import org.junit.Test;

import java.util.concurrent.*;


/**
 * @author xiaoniu 2019/6/21.
 */
public class ThreadPoolDemo {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // 创建 Result 对象 r
        Result r = new Result();
        r.setAAA("a");
        // 提交任务
        Future<Result> future =  executor.submit(new Task(r), r);
        Result fr = future.get();
        // 下面等式成立
        System.out.println("fr === r is "+ (fr == r));
        System.out.println("fr.getAAA === a is "+ ("a".equals(fr.getAAA())));
        System.out.println("fr.getXXX === x is "+ ("x".equals(fr.getXXX())));
    }

    class Task implements Runnable {
        Result r;

        // 通过构造函数传入 result
        Task(Result r) {
            this.r = r;
        }
        @Override
        public void run() {
            // 可以操作 result
            String a = r.getAAA();
            System.out.println("run...a..."+a);
            r.setXXX("x");
        }
    }
    class Result {
        String AAA;
        String XXX;

        public String getAAA() {
            return AAA;
        }

        public void setAAA(String AAA) {
            this.AAA = AAA;
        }

        public String getXXX() {
            return XXX;
        }

        public void setXXX(String XXX) {
            this.XXX = XXX;
        }
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        //创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(
                ()-> 1+2
        );
        //创建并启动线程
        Thread t1 = new Thread(futureTask);
        t1.start();
        //获取结算结果
        Integer result = futureTask.get();
        //3
        System.out.println(result);
    }

    @Test
    public void test3(){
        // 任务 1：洗水壶 -> 烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(()->{
                    System.out.println("T1: 洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1: 烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    System.out.println("T2: 洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2: 洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2: 拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return " 龙井 ";
                });
        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf)->{
                    System.out.println("T1: 拿到茶叶:" + tf);
                    System.out.println("T1: 泡茶...");
                    return " 上茶:" + tf;
                });
        // 等待任务 3 执行结果
        System.out.println(f3.join());


        /** 一次执行结果：
        T1: 洗水壶...
        T2: 洗茶壶...
        T1: 烧开水...
        T2: 洗茶杯...
        T2: 拿茶叶...
        T1: 拿到茶叶: 龙井
        T1: 泡茶...
        上茶: 龙井**/

    }

    private void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }

    @Test
    public void test4(){
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t = getRandom(5, 10);
                    System.out.println("f1 t="+t);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = getRandom(5, 10);
                    System.out.println("f2 t="+t);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s);

        System.out.println(f3.join());

    }

    private int getRandom(int min, int max) {
        return (int)(1+Math.random()*(max-min)+1);
    }

    @Test
    public void test5(){
        System.out.println((Math.random()*10));
        System.out.println((int)(1+Math.random()*10));
    }

    @Test
    public void test6(){
        CompletableFuture<Integer>
                f0 = CompletableFuture
                .supplyAsync(()->(7/0))
                .thenApply(r->r*10)
                .exceptionally(e->0)
                .whenComplete(
                        (consumer,action)->{
                    System.out.println(consumer);
                    System.out.println("000000");
                    System.out.println(action);
                });
        System.out.println(f0.join());

    }
    @Test
    public void test7(){
        CompletableFuture<Integer>
                f0 = CompletableFuture
                .supplyAsync(()->(7/0))
                .thenApply(r->r*10)
                .exceptionally(e->0)
                .handle((f1,f2)->{
                 return 1;
            });
        System.out.println(f0.join());

    }

    @Test
    public void test8(){

        // 采购订单
        String  po ;
        CompletableFuture<Boolean> cf =
                CompletableFuture.supplyAsync(()->{
                    // 在数据库中查询规则
                    System.out.println("在数据库中查询规则...");
                    sleep(2,TimeUnit.SECONDS);
                    return true;
                }).thenApply(r -> {
                    // 规则校验
                    System.out.println("规则校验...");
                    sleep(2,TimeUnit.SECONDS);
                    return true;
                });

        doOtherSomeThing();

        Boolean isOk = cf.join();
        System.out.println("isOk="+isOk);

    }


    private void doOtherSomeThing() {
        System.out.println("hello main...");
        sleep(1,TimeUnit.SECONDS);
        System.out.println("hello main...1 seconds...");

    }
}
