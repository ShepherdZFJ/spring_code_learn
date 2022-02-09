package com.shepherd.jdk;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/2/8 17:37
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        synchronizedDemo.method();
    }
    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
}
