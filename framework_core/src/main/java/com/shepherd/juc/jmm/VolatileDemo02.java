package com.shepherd.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/1/26 18:41
 */
public class VolatileDemo02 {
    // volatile 不保证原子性
//    private volatile static Integer num = 0;

    // 原子类的 Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add(){
//         num++; // 不是一个原子性操作
//        num.getAndIncrement(); // AtomicInteger + 1 方法， CAS
    }

    public static void main(String[] args) {

        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000 ; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){ // main  gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);


    }
}
