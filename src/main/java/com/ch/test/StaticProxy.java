package com.ch.test;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Description 接口
 * @Author caihao
 * @Date 2019/9/3 15:41
 * @Param
 * @Return
 */
interface ClothFactory{

    void produceCloth();
}


/**
 * @Description 被代理类
 * @Author caihao
 * @Date 2019/9/3 15:44
 * @Param
 * @Return
 */
@Data
class Nike implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("Nike衣服工厂生产衣服。。。");
    }
}


/**
 * @ClassName: StaticProxy
 * @Description: 代理类（静态代理）
 * @Author: caihao
 * @Date: 2019/9/3 15:34
 */
public class StaticProxy implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("代理类开始收取代理费用1000$");
        Nike nike = new Nike();
        nike.produceCloth();
    }
}






