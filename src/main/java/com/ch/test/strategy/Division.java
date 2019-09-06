package com.ch.test.strategy;

/**
 * @ClassName: Division
 * @Description: 具体策略角色 ConcreteStrategy ,两个数做除法运算
 * @Author: caihao
 * @Date: 2019/9/6 15:29
 */
public class Division implements MathStrategy{
    @Override
    public int math(int a, int b) {
        return a / b;
    }
}
