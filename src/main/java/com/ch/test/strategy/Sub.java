package com.ch.test.strategy;

/**
 * @ClassName: Sub
 * @Description: 具体策略角色 ConcreteStrategy ,两个数做减法
 * @Author: caihao
 * @Date: 2019/9/6 15:26
 */
public class Sub implements MathStrategy{
    @Override
    public int math(int a, int b) {
        return a - b;
    }
}
