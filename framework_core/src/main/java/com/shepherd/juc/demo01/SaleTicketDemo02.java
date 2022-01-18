package com.shepherd.juc.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/1/17 14:36
 */

/**
 * Synchronized和Lock的区别
 * 1、Synchronized 内置的Java关键字， Lock是一个Java类
 * 2、Synchronized 无法判断获取锁的状态，Lock可以判断是否获取到了锁。
 * 3、Synchronized 会自动释放锁，lock必须手动释放锁，不释放就会造成死锁。
 */
public class SaleTicketDemo02  {
    public static void main(String[] args) {

        // 并发：多线程操作同一个资源类, 把资源类丢入线程
        Ticket2 ticket = new Ticket2();


        // @FunctionalInterface 函数式接口，jdk1.8  lambda表达式 (参数)->{ 代码 }
        new Thread(()->{for (int i = 1; i < 500 ; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 1; i < 500 ; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 1; i < 500 ; i++) ticket.sale();},"C").start();


    }
}

// Lock三部曲
// 1、 new ReentrantLock();
// 2、 lock.lock(); // 加锁
// 3、 finally=>  lock.unlock(); // 解锁
class Ticket2 {
    // 属性、方法
    private int number = 500;

    // ReentrantLock 可重入锁，默认为非公平锁
    Lock lock = new ReentrantLock();

    public void sale(){

        lock.lock(); // 加锁

        try {
            // 业务代码

            if (number>0){
                System.out.println(Thread.currentThread().getName()+"卖出了"+(number--)+"票,剩余："+number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁
        }
    }

}
