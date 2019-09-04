package com.ch.test.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName: CglibTest
 * @Description: cglib带动态代理测试类
 * @Author: caihao
 * @Date: 2019/9/4 9:04
 */
public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //  继承被代理类
        enhancer.setSuperclass(Hello.class);
        //  设置回调
        enhancer.setCallback(new MyMethodInterceptor());
        //  生成代理对象
        Hello hello = (Hello)enhancer.create();
        //  在调用代理中调用方法会被我们实现的方法拦截器拦截
        hello.sayHello();
    }

}

/**
 * 被代理类（没有实现接口）
 */
class Hello{

    public void sayHello(){
        System.out.println("hello word!");
    }
}

/**
 * 实现MethodInterceptor接口生成方法拦截器
 */
class MyMethodInterceptor implements MethodInterceptor{

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before:"+method.getName());
        Object val = methodProxy.invokeSuper(o, objects);
        System.out.println("After:"+method.getName());
        return val;
    }
}