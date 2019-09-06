package com.ch.test.strategy;

/**
 * @ClassName: Add
 * @Description: 具体策略角色 ConcreteStrategy ,两个数做加法运算
 * @Author: caihao
 * @Date: 2019/9/6 15:25
 */
public class Add implements MathStrategy{
    @Override
    public int math(int a, int b) {
        return a + b;
    }
}
