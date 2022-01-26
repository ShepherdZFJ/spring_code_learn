package com.shepherd.juc.function;

import java.util.function.Function;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/1/26 15:45
 */
public class FunctionDemo {
    /**
     * Function 函数型接口, 有一个输入参数，有一个输出
     * 只要是 函数型接口 可以 用 lambda表达式简化
     */
    public static void main(String[] args) {
        //
//        Function<String,String> function = new Function<String,String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };

        Function<String,String> function = str->{return str;};

        System.out.println(function.apply("shepherd"));
    }
}
