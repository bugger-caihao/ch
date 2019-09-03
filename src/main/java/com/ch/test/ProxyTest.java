package com.ch.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: ProxyTest
 * @Description: 动态代理测试类
 * @Author: caihao
 * @Date: 2019/9/3 21:58
 */
public class ProxyTest {

    public static void main(String[] args) {
        //  1.创建一个被代理的对象
        Man man  = new Man();
        //  2.创建代理对象
        MyInvocationHandler handler = new MyInvocationHandler();
        Human human = (Human)handler.newProxyInstance(man);
        //  3.调用方法，实质上是掉用 invoke 方法
        human.show();
        System.out.println("======================");
        human.info();

        System.out.println();

        Nike nike = new Nike();
        ClothFactory factory = (ClothFactory) handler.newProxyInstance(nike);
        factory.produceCloth();

    }

}

/**
 * 接口
 */
interface Human{

    void info();

    void show();
}

/**
 * 被代理类（实现了 Human接口）
 */
class Man implements Human{

    @Override
    public void info() {
        System.out.println("我是超人我怕谁。。。");
    }

    @Override
    public void show() {
        System.out.println("展示我的风采。。。");
    }
}

/**
 * 动态代理类：要实现 InvocationHandler 接口
 */
class MyInvocationHandler implements InvocationHandler{
    private Object obj;

    public Object newProxyInstance(Object obj) {
        this.obj = obj;
        //  通过静态方法 Proxy.newProxyInstance() 根据传入的目标返回一个代理对象
        //  该方法用于为指定类装载器、一组接口及调用处理器生成动态代理类实例
        //  第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //  第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //  第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //  在这里对方法进行加强，AOP的原理
        System.out.println("事务开始。。。");
        Object val = method.invoke(this.obj, args);
        System.out.println("事务的关闭。。。");
        return val;
    }
}