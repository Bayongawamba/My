package com.jacky.zhang.thread;

import java.util.concurrent.Exchanger;

//交换  用于两个工作线程之间交换数据的封装工具类，简单说就是一个线程在完成一定的事务后想与另一个线程交换数据，
// 则第一个先拿出数据的线程会一直等待第二个线程，直到第二个线程拿着数据到来时才能彼此交换对应数据
//假如T2线程没有exchange，那么t1将会阻塞在exchange,直到t2运行exchange
public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(()->{
            String s = "T1";
            try {
                s=exchanger.exchange(s);
                System.out.println(Thread.currentThread().getName()+" "+s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            String s = "T2";
            try {
                s=exchanger.exchange(s);
                System.out.println(Thread.currentThread().getName()+" "+s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
