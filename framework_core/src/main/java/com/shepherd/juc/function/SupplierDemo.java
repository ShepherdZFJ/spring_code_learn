package com.shepherd.juc.function;

import java.util.function.Supplier;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/1/26 15:48
 */
public class SupplierDemo {
    /**
     * Supplier 供给型接口 没有参数，只有返回值
     */
    public static void main(String[] args) {
//        Supplier supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                System.out.println("get()");
//                return 1024;
//            }
//        };

        Supplier supplier = ()->{ return 1024; };
        System.out.println(supplier.get());
    }
}
