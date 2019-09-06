package com.ch.test.strategy;

/**
 * @ClassName: Mulpi
 * @Description: 具体策略角色 ConcreteStrategy ,两个数做乘法运算
 * @Author: caihao
 * @Date: 2019/9/6 15:27
 */
public class Mulpi implements MathStrategy {
    @Override
    public int math(int a, int b) {
        return a * b;
    }
}
