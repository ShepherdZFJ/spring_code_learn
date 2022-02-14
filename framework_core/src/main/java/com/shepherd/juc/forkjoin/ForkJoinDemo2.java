package com.shepherd.juc.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/2/14 13:45
 */

/**
 * Fork/Join 并行计算框架主要解决的是分治任务。分治的核心思想是“分而治之”：将一个大的任务拆分成小的子任务去解决，
 * 然后再把子任务的结果聚合起来从而得到最终结果
 *这个计算框架里的Fork 对应的是分治任务模型里的任务分解，Join 对应的是结果合并。Fork/Join 计算框架主要包含两部分，
 * 一部分是分治任务的线程池 ForkJoinPool，另一部分是分治任务 ForkJoinTask
 *Fork/Join 并行计算框架的核心组件是 ForkJoinPool。ForkJoinPool 支持任务窃取机制，能够让所有线程的工作量基本均衡，
 * 不会出现有的线程很忙，而有的线程很闲的状况，所以性能很好。Java 1.8 提供的 Stream API 里面并行流也是以 ForkJoinPool 为基础的。
 * 不过需要你注意的是，默认情况下所有的并行流计算都共享一个 ForkJoinPool，这个共享的 ForkJoinPool 默认的线程数是 CPU 的核数；
 * 如果所有的并行流计算都是 CPU 密集型计算的话，完全没有问题，但是如果存在 I/O 密集型的并行流计算，那么很可能会因为一个很慢的 I/O 计算而拖慢整个系统的性能。
 * 所以建议用不同的 ForkJoinPool 执行不同类型的计算任务
 */
public class ForkJoinDemo2 {
    public static void main(String[] args){
        // 创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        // 创建分治任务
        Fibonacci fib = new Fibonacci(10);
        // 启动分治任务
        Integer result = fjp.invoke(fib);
        // 输出结果
        System.out.println(result);
    }
    // 递归任务
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n){this.n = n;}
        protected Integer compute(){
            if (n <= 1)
                return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            // 创建子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            f2.fork();
            // 等待子任务结果，并合并结果
            return f2.join() + f1.join();
        }
    }
}
