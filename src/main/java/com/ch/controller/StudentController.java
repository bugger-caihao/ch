package com.ch.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caihao
 * @since 2019-08-05
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @RequestMapping(value = "/insert", method = RequestMethod.OPTIONS)
    //  当指定了请求的方式的时候，swagger2才会生成对应的请求的方法文档，如果没有指定请求的方式，则会默认的生成7种
    //  GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH 7种请求方式
    public void insert(){
        System.out.println(123);
    }

}
