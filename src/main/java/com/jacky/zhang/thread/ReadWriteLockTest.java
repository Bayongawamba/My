package com.jacky.zhang.thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//读写锁，ReadWriteLock同Lock一样也是一个接口，提供了readLock和writeLock两种锁的操作机制，一个是只读的锁，一个是写锁。
//读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的(排他的)。 每次只能有一个写线程，但是可以有多个线程并发地读数据。
//如果使用ReentrantLock，那么读的时候也是互斥，效率很低
public class ReadWriteLockTest {
    private static int value;

    static Lock lock = new ReentrantLock();

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v){
        try {
            lock.lock();
            Thread.sleep(5000);
            value = v;
            System.out.println("write over!");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
//        Runnable readRunnable = ()-> read(lock);

        Runnable readRunnable = ()->{read(readLock);};
//        Runnable writeRunnable = () -> write(lock, new Random().nextInt(1000));

        Runnable writeRunnable = ()->write(readLock, new Random().nextInt(1000));

        for (int i = 0; i < 18; i++) new Thread(readRunnable).start();
        for (int i = 0; i < 2; i++) new Thread(writeRunnable).start();
    }
}
