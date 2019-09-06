package com.ch.test.strategy;

/**
 * @ClassName: Context
 * @Description: 上下文角色 Context ,其职责本来是隔离客户端与策略类的耦合，让客户端完全与上下文环境沟通，无需关系具体策略
 * @Author: caihao
 * @Date: 2019/9/6 15:32
 */
public class Context {

    private MathStrategy mathStrategy;

    public Context(MathStrategy mathStrategy) {
        this.mathStrategy = mathStrategy;
    }

    public int math(int a, int b){
        return this.mathStrategy.math(a, b);
    }
}
