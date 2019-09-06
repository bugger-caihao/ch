package com.ch;

import com.ch.entity.UserInfo;
import com.ch.utils.JsonResult;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    @Test
    public void getHttp() throws UnsupportedEncodingException {

        //  获取rest客户端对象
        RestTemplate restTemplate = new RestTemplate();


        //  解决（响应数据中可能出现的）中文乱码问题
        List<HttpMessageConverter<?>> convertersList = restTemplate.getMessageConverters();
        //  移出原来的转化器
        convertersList.remove(1);
        //  设置字符编码为UTF-8
        HttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        //  添加新的转换器（convert顺序错误会导致失败）
        convertersList.add(1, converter);
        restTemplate.setMessageConverters(convertersList);

        //  请求头信息
        //  HttpHeaders实现了MultiValueMap接口
        HttpHeaders headers = new HttpHeaders();
        // 给请求header中添加一些数据
        headers.add("ch", "哈哈哈");

        //  注:GET请求 创建HttpEntity时,请求体传入null即可
        //  请求体的类型任选即可;只要保证 请求体 的类型与HttpEntity类的泛型保持一致即可
        String httpBody = null;
        HttpEntity<String> httpEntity = new HttpEntity<String>(httpBody, headers);

        //  设置 URI 地址
        StringBuffer paramURI = new StringBuffer("http://127.0.0.1:8080/hello");
        // 字符数据最好encoding一下;这样一来，某些特殊字符才能传过去(如:flag的参数值就是“&”,不encoding的话,传不过去)
        paramURI.append("/" + URLEncoder.encode("哈哈哈", "utf-8"));
        paramURI.append("/" + URLEncoder.encode("是", "utf-8"));
        URI uri = URI.create(paramURI.toString());

        //  执行请求并返回响应的结果
        //  此处的泛型对应响应体数据类型;即:这里指定响应体的数据装配为String
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        //  状态码
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        System.out.println("========================================");

        Gson gson = new Gson();
        String s = gson.toJson(response.getHeaders());
        String s1 = gson.toJson(response.getBody());
        System.out.println("响应头： "+s);
        System.out.println("响应体： "+s1);
    }

    @Test
    public void postHttp() throws UnsupportedEncodingException {

        //  获取rest客户端对象
        RestTemplate restTemplate = new RestTemplate();


        //  解决（响应数据中可能出现的）中文乱码问题
        List<HttpMessageConverter<?>> convertersList = restTemplate.getMessageConverters();
        //  移出原来的转化器
        convertersList.remove(1);
        //  设置字符编码为UTF-8
        HttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        //  添加新的转换器（convert顺序错误会导致失败）
        convertersList.add(1, converter);
        restTemplate.setMessageConverters(convertersList);

        //  请求头信息
        //  HttpHeaders实现了MultiValueMap接口
        HttpHeaders headers = new HttpHeaders();
        // 给请求header中添加一些数据
        headers.add("ch", "哈哈哈");

        //  post请求：请求体的类型任选即可，只要保证请求体的类型与HttpEntity类的泛型保持一致即可
        Gson gson = new Gson();

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(10l);
        userInfo.setUserName("哈哈");
        userInfo.setUserPassword("123456");

        HttpEntity<UserInfo> httpEntity = new HttpEntity<UserInfo>(userInfo, headers);

        //  设置 URI 地址
        StringBuffer paramURI = new StringBuffer("http://127.0.0.1:8080/hello");
        //  字符数据最好encoding一下;这样一来，某些特殊字符才能传过去(如:flag的参数值就是“&”,不encoding的话,传不过去)

        /*String json = gson.toJson(userInfo);
        paramURI.append("/"+URLEncoder.encode(json, "utf-8"));*/
        URI uri = URI.create(paramURI.toString());

        //  执行请求并返回响应的结果
        //  此处的泛型对应响应体数据类型;即:这里指定响应体的数据装配为String
        ResponseEntity<JsonResult> response = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, JsonResult.class);

        //  状态码
        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        System.out.println("========================================");


        String s = gson.toJson(response.getHeaders());
        String s1 = gson.toJson(response.getBody());
        System.out.println("响应头： "+s);
        System.out.println("响应体： "+s1);
    }

    @Test
    public void test05(){
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        String reomveName = "赵六";
        /*for (String s : list) {
            if(reomveName.equals(s)){
                //  for循环在编译的时候，会被默认的编译成遍历器，这里调用的是list的remove()方法，源码中会验证checkForComodification();
                list.remove(s);
            }
        }*/
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String next = it.next();
            if(reomveName.equals(next)){
                it.remove();
            }
        }
        System.out.println(list);

    }







}
