package com.ch.lambda;

import com.ch.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: LambdaTest
 * @Description: lambda 表达式替换掉匿名内部类
 * @Author: caihao
 * @Date: 2019/9/6 8:49
 */
public class LambdaTest {

    List<Student> list = Arrays.asList(
            new Student(1, "张三" , 100),
            new Student(2, "李四" , 50),
            new Student(3, "王五" , 23),
            new Student(4, "赵六" , 66),
            new Student(5, "田七" , 12)
    );

    /**
     * @Description 查询 id>3 的记录
     * @Author caihao
     * @Date 2019/9/6 9:15
     * @Param [list]
     * @Return java.util.List<com.ch.entity.Student>
     */
    public List<Student> selectId(List<Student> list){
        //  后面的 <> 中不用写泛型类型，属于 java8 的泛型推断特性
        List<Student> valList = new ArrayList<>();

        for (Student student : list) {
            if(student.getStudentId()> 3){
                valList.add(student);
            }
        }

        return valList;
    }

    /**
     * @Description 查询关联id在50以下的记录
     * @Author caihao
     * @Date 2019/9/6 9:15
     * @Param [list]
     * @Return java.util.List<com.ch.entity.Student>
     */
    public List<Student> selectRelId(List<Student> list){
        //  后面的 <> 中不用写泛型类型，属于 java8 的泛型推断特性
        List<Student> valList = new ArrayList<>();

        for (Student student : list) {
            if(student.getRelId()< 50){
                valList.add(student);
            }
        }

        return valList;
    }

    /**
     * @Description 策略设计模式
     * @Author caihao
     * @Date 2019/9/6 9:53
     * @Param [list, strategyInterface]
     * @Return java.util.List<com.ch.entity.Student>
     */
    public List<Student> selectStrategy(List<Student> list, StrategyInterface strategyInterface){
        List<Student> valList = new ArrayList<>();

        for (Student student : list) {
            if(strategyInterface.strategy(student)){
                valList.add(student);
            }
        }

        return valList;
    }

    @Test
    public void test1(){
        //  需求1：查询 id>3 的记录
        List<Student> students = selectId(list);
        students.forEach(System.out::println);

        System.out.println("-----------------------------");

        //  需求2：查询关联id在50以下的记录
        List<Student> students1 = selectRelId(list);
        students1.forEach(System.out::println);
        //  这种方式代码冗余较多
    }

    /**
     * 优化方案一：使用策略者模式,写两个class来实现接口
     */
    @Test
    public void test2(){
        List<Student> students = selectStrategy(list, new IdStrategy());
        students.forEach(System.out::println);

        System.out.println("--------------------------");

        List<Student> students1 = selectStrategy(list, new RelIdStrategy());
        students1.forEach(System.out::println);
    }

    /**
     * 优化方案二：使用匿名内部类来申明
     */
    @Test
    public void test3(){
        //  查询出id>3的学生
        List<Student> students = selectStrategy(list, new StrategyInterface() {
            @Override
            public boolean strategy(Student student) {
                return student.getStudentId() > 3;
            }
        });
        students.forEach(System.out::println);

        System.out.println("----------------------------");

        //  查询出关联id在50以下的学生
        List<Student> students1 = selectStrategy(list, new StrategyInterface() {
            @Override
            public boolean strategy(Student student) {
                return student.getRelId() < 50;
            }
        });
        students1.forEach(System.out::println);

    }

    /**
     * 优化方案二：使用 lambda 表达式
     * */
    @Test
    public void test4(){
        List<Student> students = selectStrategy(list, (student) -> student.getRelId() < 50);
        students.forEach(System.out::println);

        System.out.println("-------------------------");

        List<Student> students1 = selectStrategy(list, student -> student.getStudentId() < 3);
        students1.forEach(System.out::println);
    }



}
