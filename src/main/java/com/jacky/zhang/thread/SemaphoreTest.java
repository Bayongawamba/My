package com.jacky.zhang.thread;

import java.util.concurrent.Semaphore;

/*信号，允许多个线程同时访问
* Semaphore的主要方法摘要：
　　void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
　　void release():释放一个许可，将其返回给信号量。
　　int availablePermits():返回此信号量中当前可用的许可数。
　　boolean hasQueuedThreads():查询是否有线程正在等待获取。
*
* */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
//        Semaphore semaphore = new Semaphore(2,true);

        new Thread(()->{
           try {
               semaphore.acquire();

               System.out.println("T1 running……");
               Thread.sleep(200);
               System.out.println("T1 running……");
           }catch (InterruptedException e){
               e.printStackTrace();
           }finally {
               semaphore.release();
           }
        }).start();

        new Thread(()->{
            try {
                semaphore.acquire();

                System.out.println("T2 running……");
                Thread.sleep(200);
                System.out.println("T2 running……");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();
    }
}
