package com.ch.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockTest
 * @Description: 重入锁
 * @Author: caihao
 * @Date: 2019/9/21 9:03
 */
public class ReentrantLockTest {

    //  不带参数的是不公平锁（不去计算线程等待的时间，线程调度器随机的分配），
    //  当参数为true时，创建的是公平锁（哪个线程等待的时间长就先让哪个线程先执行）
    //  ReentrantLock 手动上锁，异常的时候，jvm不会释放锁，要自己手动释放锁

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Runnable runnable = () -> {
            try {
                lock.lock();
                for(int i=0; i<10; i++){

                    System.out.println(Thread.currentThread().getName()+":");
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        };

        new Thread(runnable, "thread-1").start();
        new Thread(runnable, "thread-2").start();

    }



}
