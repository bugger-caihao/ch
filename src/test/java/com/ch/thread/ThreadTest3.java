package com.ch.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ThreadTest3
 * @Description:
 * @Author: caihao
 * @Date: 2019/9/20 11:34
 */
public class ThreadTest3 {

    /*volatile*/ List lists = new ArrayList();

    public void add(Object object){
        lists.add(object);
    }

    public int size(){
        return lists.size();
    }



    public static void main(String[] args) {
        ThreadTest3 test3 = new ThreadTest3();
        final Object o = new Object();

        new Thread(() -> {
            synchronized (o){
                System.out.println("thread-2 启动");
                if(test3.size() != 5){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("thread-2 结束");
                o.notify();
            }
        }, "thread-2").start();


        new Thread(() -> {
            System.out.println("thread-1 启动");
            synchronized (o){

                for(int i=0; i<10; i++){
                    test3.add(new Object());
                    System.out.println("add:"+i);
                    if(test3.size() == 5){
                        o.notify();

                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "thread-1").start();



    }
}




