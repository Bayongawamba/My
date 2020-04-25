package com.jacky.zhang.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    //CountDownLatch 计数器门栓 ，每个线程结束，latch.countDown()减少1， 当latch减少到0时才执行，否则latch.await()处堵塞住。
    public static void count() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"start");
                latch.countDown();
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        try {
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("latch end……");
    }

    public static void main(String[] args) {
        count();
    }

}
