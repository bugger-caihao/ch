package com.ch.test;

/**
 * @ClassName: Test
 * @Description:
 * @Author: caihao
 * @Date: 2019/7/1 19:21
 */
public class Test01 {

    public static void main(String[] args) {
        //  switch中表达式的值可以为整型，字节类型，字符串类型，但是为字符串类型的时候不可以为空
        //  同样的case中的值也不可以为null
        String a = "12";
        switch (a){
            case "12":
                System.out.println("匹配到值12");
                break;
            case "34":
                System.out.println("匹配到值34");
                break;
           /* case null:
                System.out.println("匹配到值null");
                break;*/
        }

    }
}
