package com.ch.thread;

import java.util.LinkedList;

/**
 * @ClassName: MyContainer
 * @Description: wait 和 notify、notifyAll来写一个生产者消费值模型
 * @Author: caihao
 * @Date: 2019/9/21 14:28
 */
public class MyContainer {

    final private LinkedList list = new LinkedList();

    final private int MAX = 10;

    private int count = 0;

    public synchronized void put(Object o){
        while(list.size() == MAX){
            try {
                this.wait();//  容器满了，生产者线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(o);
        System.out.println(Thread.currentThread().getName()+"生产了一个产品");
        count++;
        this.notifyAll();//  通知消费者消费
    }

    public synchronized Object get(){
        while (list.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object o = list.removeFirst();
        count --;
        this.notifyAll();
        return o;
    }

    public static void main(String[] args) {

        MyContainer c = new MyContainer();

        for(int i=0; i<10; i++){
            new Thread(() -> System.out.println(c.get()), "c-"+i).start();
        }

        for(int j=0; j<2; j++){
            new Thread(() -> c.put(new Object()), "p-"+j).start();
        }


    }





}
