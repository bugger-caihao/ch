package com.ch.test.proxy;

/**
 * @ClassName: StaticProxy
 * @Description: 代理类（静态代理）
 * @Author: caihao
 * @Date: 2019/9/3 15:34
 */
public class StaticProxy implements ClothFactory{

    private ClothFactory clothFactory;

    public StaticProxy(ClothFactory clothFactory){
        this.clothFactory = clothFactory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理类开始收取代理费用1000$");
        clothFactory.produceCloth();
    }
}






