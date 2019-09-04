package com.ch.test.proxy;

import lombok.Data;

/**
 * @ClassName: Nike
 * @Description: 被代理类
 * @Author: caihao
 * @Date: 2019/9/3 16:29
 */
@Data
public class Nike implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("Nike衣服工厂生产衣服。。。");
    }
}

