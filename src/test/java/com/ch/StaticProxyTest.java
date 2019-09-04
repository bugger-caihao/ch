package com.ch;

import com.ch.test.proxy.ClothFactory;
import com.ch.test.proxy.Nike;
import com.ch.test.proxy.StaticProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: StaticProxyTest
 * @Description: 今天代理测试类
 * @Author: caihao
 * @Date: 2019/9/3 15:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StaticProxyTest {

    @Test
    public void test(){
        ClothFactory clothFactory = new StaticProxy(new Nike());
        clothFactory.produceCloth();

    }
}
