package com.shepherd.juc.future;

import java.util.concurrent.*;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/2/14 11:46
 */

/**
 * 当需要批量提交异步任务的时候建议你使用 CompletionService。CompletionService 将线程池 Executor 和阻塞队列 BlockingQueue
 * 的功能融合在了一起，能够让批量异步任务的管理更简单。除此之外，CompletionService 能够让异步任务的执行结果有序化，先执行完的先进入阻塞队列，
 * 利用这个特性，你可以轻松实现后续处理的有序性，避免无谓的等待
 */
public class CompletionServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       testCompletionService();
    }

    public static ExecutorService fixedThreadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*2,
            Runtime.getRuntime().availableProcessors() * 40,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(Runtime.getRuntime().availableProcessors() * 20),
            Executors.defaultThreadFactory());

    public static void testCompletionService() throws InterruptedException, ExecutionException {
        CompletionService<Integer> cs = new ExecutorCompletionService(fixedThreadPool);

        // 任务1
        cs.submit(()->{
            System.out.println("=====执行任务1");
            TimeUnit.SECONDS.sleep(5);
            return 10;
        });

        // 任务2
        cs.submit(()->{
            System.out.println("=====执行任务2");
            TimeUnit.SECONDS.sleep(1);
            return 20;
        });

        // 任务3
        cs.submit(()->{
            System.out.println("=====执行任务3");
            TimeUnit.SECONDS.sleep(3);
            return 30;
        });

        for (int i=0; i<3;i++) {
            Integer rs = cs.take().get();
            System.out.println("rs=" + rs*10);
        }
    }

    public static void test() {

    }
}
