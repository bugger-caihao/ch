package com.ch.thread;

/**
 * @ClassName: ThreadTest1
 * @Description: 开启一个线程
 * @Author: caihao
 * @Date: 2019/9/20 9:08
 */
public class ThreadTest1 {

    public static void main(String[] args) {
        //  开启线程的两种方式：1.继承Thread类，重写run方法；2.实现Runnable接口，重写run方法
        Runnable runnable = () -> {
            for (int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+"输出："+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        thread1.setName("thread-1");
        Thread thread2 = new Thread(runnable, "thread-2");

        thread1.start();
        thread2.start();

    }
}
