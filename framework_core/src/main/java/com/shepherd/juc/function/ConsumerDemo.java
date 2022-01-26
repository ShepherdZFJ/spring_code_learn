package com.shepherd.juc.function;

import java.util.function.Consumer;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/1/26 15:48
 */
public class ConsumerDemo {
    /**
     * Consumer 消费型接口: 只有输入，没有返回值
     */
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String str) {
//                System.out.println(str);
//            }
//        };
        Consumer<String> consumer = (str)->{System.out.println(str);};
        consumer.accept("shepherd");

    }
}
