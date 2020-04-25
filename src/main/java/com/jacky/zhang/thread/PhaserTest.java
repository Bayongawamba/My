package com.jacky.zhang.thread;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

//栅栏组 当线程都达到一个阶段，才会执行下一个阶段
public class PhaserTest {

    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7);

        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }

        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarriagePhaser extends Phaser {
        @Override
        //phase 阶段从0开始  registeredParties 线程数量
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘拥抱" + registeredParties);
                    System.out.println();
                    return false;
                case 4:
                    System.out.println("入洞房，生孩子" + registeredParties);
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s到达现场\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s吃完\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s离开\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        private void hug() {
            milliSleep(r.nextInt(1000));
            if (name.equals("新郎") || name.equals("新娘")) {
                System.out.printf("%s 洞房\n", name);
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

//        public void birth() {
//            milliSleep(r.nextInt(1000));
//            System.out.printf("%s生孩子\n", name);
//            phaser.arriveAndAwaitAdvance();
//        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
//            birth();
        }
    }

    static void milliSleep(int milli){
        try {
            TimeUnit.MICROSECONDS.sleep(milli);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
