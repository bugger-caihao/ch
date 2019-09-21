package com.ch;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.ch.entity.Student;
import com.ch.entity.StudentInfo;
import org.junit.Test;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * @ClassName: LambdaTest
 * @Description: 四大内置核心函数接口
 * @Author: caihao
 * @Date: 2019/9/6 22:09
 */
public class LambdaTest {
    /**
     * 消费型接口
     * Consumer<T>{
     *     void accept(T t);
     * }
     *
     * 供给型接口
     * Supplier<T>{
     *     T get();
     * }
     *
     * 函数型接口
     * Function<T, R>{
     *     R apply(T t);
     * }
     *
     * 断言型接口
     * Predicate<T>{
     *     boolean test(T t);
     * }
     *
     */

    @Test
    public void test1(){
        //  测试消费型接口 Consumer<T>
        useMoney(1000d, (m) -> System.out.println("周末出门花费了"+m+"元"));
    }

    public void useMoney(double money, Consumer<Double> con){
        con.accept(money);
    }


    @Test
    public void test2(){
        //  测试供给型接口 Supplier<T>
        //  产生指定个数的整数，并放入集合中
        List<Integer> list = getNumList(10, () -> (int)(Math.random() * 100));
        list.forEach(System.out::println);
    }


    public List<Integer> getNumList(int a, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a; i++){
            list.add(sup.get());
        }
        return list;
    }


    @Test
    public void test3(){
        //  测试函数型接口 Function<T, R>
        //  需求：操作字符串，去除首尾或者截取
        String s1 = strOperate(" hahha  去除首尾空格  ", (s) -> s.trim());
        System.out.println(s1);
        String s2 = strOperate(" hahha  去除首尾空格  ", (s) -> s.toUpperCase());
        System.out.println(s2);
    }

    public String strOperate(String str, Function<String, String> fun){
        return fun.apply(str);
    }


    @Test
    public void test4(){
        //  测试断言型接口 Predicate<T>
        //  需求：满足条件的字符串放入到集合中去
        List<String> list = Arrays.asList("哈哈", "嘻嘻嘻", "则啧啧啧", "啊啊啊啊啊", "abcde");
        List<String> list1 = filterStr(list, (s) -> s.length() > 3);
        list1.forEach(System.out::println);

        System.out.println("---------------------------------");

        List<String> list2 = filterStr(list, (s) -> s.contains("abc"));
        list2.forEach(System.out::println);

        /*LocalDateTime localDateTime = LocalDateTime.of(2019, 12, 21, 19, 30, 59);
        System.out.println(localDateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        String format = localDateTime.format(dateTimeFormatter);
        System.out.println(format);*/
    }

    public List<String> filterStr(List<String> list, Predicate<String> pre){
        List<String> valueList = new ArrayList<>();

        for (String s : list) {
            if(pre.test(s)){
                valueList.add(s);
            }
        }
        return valueList;
    }


    /**
     *  方法的引用
     *  1.对象::实例方法
     *  2.类::静态方法
     *  3.类::实例方法
     */
    @Test
    public void test5(){
        //  对象::实例方法
        //  Lambda 表达式
        Consumer<String> con1 = (x) -> System.out.println(x);
        con1.accept("hello Lambda!");

        System.out.println("=======================");

        //  Lambda 方法引用
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("hello Lambda 方法引用");

        System.out.println("=======================");

        //  更加简化一步
        Consumer<String> con3 = System.out::println;
        con3.accept("这是简化后最终的 Lambda 方法引用");

        System.out.println("=======================");

        //  普通的 Lambda 表达式
        StudentInfo student = new StudentInfo();
        student.setStudentAge(18);
        student.setStudentName("蔡浩");
        Supplier<Integer> sup = () -> student.getStudentAge();
        System.out.println(sup.get());

        System.out.println("=======================");
        //  对象::实例方法
        Supplier<Integer> sup1 = student::getStudentAge;
        System.out.println(sup1.get());
        Supplier<String> sup2 = student::getStudentName;
        System.out.println(sup2.get());
    }

    @Test
    public void test6(){


        //  普通 Lambda 表达式
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        int result = com.compare(1, 2);
        System.out.println(result);

        System.out.println("=======================");

        //  类::静态方法
        Comparator<Integer> com1 = Integer::compare;
        int result1 = com1.compare(100, 10);
        System.out.println(result1);
    }

    /**
     * 构造方法的引用
     * 类名::new
     *
     */
    @Test
    public void test7(){
        //  普通的 Lambda 表达式
        Supplier<StudentInfo> sup = () -> new StudentInfo();
        StudentInfo studentInfo = sup.get();
        System.out.println(studentInfo);

        System.out.println("=======================");

        //  类名::new
        Supplier<StudentInfo> sup1 = StudentInfo::new;
        StudentInfo studentInfo1 = sup1.get();
        System.out.println(studentInfo1);
    }


}
