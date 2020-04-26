package com.jacky.zhang.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

//LockSupport是一个线程阻塞工具类，所有的方法都是静态方法，
// 可以让线程在任意位置阻塞，当然阻塞之后肯定得有唤醒的方法
//unpark可以优先于park方法，这样park将不会阻塞
public class LockSupportTest {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
                    if(i==5){
                        LockSupport.park();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 8 seconds");
        LockSupport.unpark(t);
    }
}
