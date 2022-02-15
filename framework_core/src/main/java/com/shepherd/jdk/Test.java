package com.shepherd.jdk;

/**
 * @author fjzheng
 * @version 1.0
 * @date 2022/2/10 12:03
 */
public class Test {
    private long count = 0;
    synchronized long get(){
        return count;
    }
    synchronized void set(long v){
        count = v;
    }
    void add10K() {
        int idx = 0;
        while(idx++ < 100000) {
            set(get()+1);
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.add10K();
        new Thread(()->{
            test.add10K();
        }).start();
        new Thread(()->{
            test.add10K();
        }).start();
        System.out.println(test.count);

    }
}
