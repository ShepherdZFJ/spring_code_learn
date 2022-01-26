package com.shepherd.juc.function;

import java.util.function.Predicate;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/1/26 15:46
 */
public class PredicateDemo {
    /**
     * 断定型接口：有一个输入参数，返回值只能是 布尔值！
     */
    public static void main(String[] args) {
        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>(){
////            @Override
////            public boolean test(String str) {
////                return str.isEmpty();
////            }
////        };

        Predicate<String> predicate = (str)->{return str.isEmpty(); };
        System.out.println(predicate.test(""));

    }
}
