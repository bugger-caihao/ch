package com.ch.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadTest4
 * @Description:
 * @Author: caihao
 * @Date: 2019/9/20 19:42
 */
public class ThreadTest4 {
    List lists = new ArrayList();

    public void add(Object object) {
        lists.add(object);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        ThreadTest4 t = new ThreadTest4();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {

                System.out.println("thread-2 启动");
                if(t.size() != 5){
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("thread-2 结束");

        }, "thread-2").start();


        new Thread(() -> {
            System.out.println("thread-1 启动");

                for(int i=0; i<10; i++){
                    t.add(new Object());
                    System.out.println("add:"+i);
                    if(t.size() == 5){
                        latch.countDown();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }, "thread-1").start();


    }
}
