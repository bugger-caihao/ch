package com.ch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ChApplicationTests {


    @Test
    public void contextLoads() {
        int[] intArray = {1, 2, 3};
        List<int[]> ints = Arrays.asList(intArray);
        //ints.add("hello");        //  java.lang.UnsupportedOperationException
        //ints.remove(2);       //  java.lang.UnsupportedOperationException
        //ints.clear();               //  java.lang.UnsupportedOperationException
    }
    
    @Test
    public void test(){
        int[] intArray = {1, 2, 3};
        List list = new ArrayList(Arrays.asList(intArray));
        //  不报错
        list.add(4);
        list.remove(1);
        list.clear();
    }

    @Test
    public void test1(){
        Integer[] intArray = {1, 2, 3};
        List list = Arrays.stream(intArray).collect(Collectors.toList());
        //  不报错
        list.add(4);
        list.remove(1);
        list.clear();
    }

    @Test
    public void test2(){
        String a = new String("123");
        String b = new String("123");
        System.out.println(a == b);
        System.out.println(a.equals(b));

        String c = "123";
        String d = "123";
        System.out.println(c == d);
        System.out.println(c.equals(d));

    }

    @Test
    public void test3(){
        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "aaabbb";
        String str4 = str1 + str2;
        String str5 = "aaa" + "bbb";
        System.out.println(str3 == str4); // false
        System.out.println(str3 == str4.intern()); //  true
        System.out.println(str3 == str5);//  true
    }




}
