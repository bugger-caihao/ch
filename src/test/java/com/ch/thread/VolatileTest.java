package com.ch.thread;

/**
 * @ClassName: VolatileTest
 * @Description: volatile 表示的是线程间可见
 * @Author: caihao
 * @Date: 2019/9/20 9:52
 */
public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        test.start();

        //  理论上修改thread中的flag值为false的时候，线程结束运行
        test.sleep(2000);
        test.flag = false;

    }
}

class Test extends Thread{
    //  加上 volatile 关键字的，表示的是线程捡可见
    /**
     * 原理是JMM（java内存模型的处理机制），cup在执行线程的时候，会先去从栈内存中读取 flag 的值为true存在缓冲区中
     * 当栈中flag 指向的值改变了为true的时候，此时cup很忙没有时间去重新读取栈中 flag 新指向的值 false，所以在线程
     * 运行的时候，cup读取到的flag的值一直是缓冲区中的值，即使栈中flag指向的值改变了，缓冲区中的flag值还是不变的
     * 当用了 volatile 关键字后，就是通知线程，一旦 volatile 修饰的变量指向的值改变了，就要cup去更新一下缓冲区的
     * 对应的值（重新读取 flag 的值到缓冲区中去）
     */
    /**
     * volatile 和 synchronized 关键字的区别：
     * 1.volatile 仅使用在变量级别； synchronized 可以修饰变量，方法，类级别上
     * 2.volatile 只能实现修饰的变量修改的可见性，不能保证原子性； synchronized 既可以变量修改保证可见性，又可以保证原子性
     * 3.volatile 不会造成线程阻塞； synchronized 可能造成线程阻塞
     */
    volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("run方法开始...");
        while (flag){

        }
        System.out.println("run方法结束...");
    }
}