package com.ch.thread;

/**
 * @ClassName: ThreadTest2
 * @Description: 死锁问题
 * @Author: caihao
 * @Date: 2019/9/20 10:52
 */
public class ThreadTest2 {

    /**
     * 死锁：两个线程或者多个线程互相持有对方的锁，并且这些线程不主动释放锁，导致线程之间互相等待，无法继续执行下去
     * 解决办法：使用 wait()、notify()、notifyAll() 方法解决
     */
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Runnable runnable1 = () -> {
            synchronized (a){
                System.out.println("线程1持有a锁");
                try {
                    //  线程等待，释放 a 锁
                    a.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b){
                    System.out.println("线程1既持有了a锁，同时又持有了b锁");
                }
            }
        };

        Runnable runnable2 = () ->{
            synchronized (b){
                System.out.println("线程2持有b锁");
                synchronized (a){
                    System.out.println("线程2既持有了b锁，同时又持有了a锁");
                    //  执行完毕，唤醒 a 锁，
                    a.notifyAll();
                }
            }
        };

        Thread thread1 = new Thread(runnable1, "thread-1");
        Thread thread2 = new Thread(runnable2, "thread-2");

        thread1.start();
        thread2.start();
    }

}
