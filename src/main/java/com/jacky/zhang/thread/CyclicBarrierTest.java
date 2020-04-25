package com.jacky.zhang.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//满20个线程，执行一次。不满20.阻塞在await
public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(20,()-> System.out.println("满人发车"));

        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (BrokenBarrierException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
