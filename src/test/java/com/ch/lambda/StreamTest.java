package com.ch.lambda;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName: StreamTest
 * @Description: java8 新特性Stream的使用
 * @Author: caihao
 * @Date: 2019/9/12 14:02
 */
public class StreamTest {
    /**
     * 注意：
     * 1.Stream 本身不会储存元素
     * 2.Stream不会改变数据源对象，相反会返回产生一个持有结果的新Stream
     * 3.Steam操作是延迟执行的，这意味着他们会等到需要结果的时候才执行。（终止操作后才执行）
     */
    /**
     * 创建流的方式
     * 1.Collection 中的stream() 方法
     * 2.Arrays 中的stream(T [] array) 静态方法创建
     */

    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void test1(){
        /*Stream<Integer> stream = list.stream();
        stream.forEach(System.out::println);*/

        List<Integer> ints = Arrays.asList(1, 2, 3);
        List<Integer> collect = ints.stream()
                .collect(Collectors.toList());
        for (Integer i : collect) {
            if(i == 2){
                collect.remove(i);
            }
        }
        collect.stream()
               .forEach(System.out::println);

    }

    /**
     * Stream 的中间操作
     *
     */
}
