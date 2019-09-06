package com.ch.test.strategy;

/**
 * @Description 抽象策略角色 Strategy ,运算的策略接口
 * @Author caihao
 * @Date 2019/9/6 15:18
 * @Param
 * @Return
 */
public interface MathStrategy {
    /**
     * 两个数的运算，存在加减乘除不同策略的运算方式
     */
    int math(int a, int b);
}
