package com.ch.test.strategy;

/**
 * @ClassName: StrategyTest
 * @Description: 策略模式测试类（客户端）
 * @Author: caihao
 * @Date: 2019/9/6 15:37
 */
public class StrategyTest {

    public static void main(String[] args) {
        //  选择一个具体策略
        //  客户端与具体策略类耦合了，而上下文环境在这里其的作用只是负责调度执行，
        //  获取结果，并没有完全起到隔离客户端与策略类的作用。
        //  一般可以通过简单工厂模式将具体策略的创建与客户端进行隔离，或者是通过 策略枚举 将上下文环境与具体策略类融合在一起，简化代码。
        //  当具体策略相对稳定时，推荐使用 策略枚举 简化代码
        MathStrategy mathStrategy = new Add();
        //  创建一个上下文
        Context context = new Context(mathStrategy);
        //  调用 Context 中的方法
        int value = context.math(1, 2);
        System.out.println(value);

        //  使用策略枚举来接触客户端和具体策略类的耦合
        int sub = Calculator.SUB.math(1, 2);
        System.out.println(sub);
        int devised = Calculator.DEVISED.math(1, 2);
        System.out.println(devised);

        //  一个类型转换的问题
        int a = 1;
        int b = 2;
        //  double c = a / b;
        //  int c = a / b;
        //  存在一个类型转换问题，两个int类型的值运算后结果为int类型，计算后0.5，int类型转换成double类型取整数部分0，小数部分丢掉，所以追中结果为0
        double c = (double) a / b;
        System.out.println(c);

    }
}
