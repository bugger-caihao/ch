package com.ch.controller;


import com.ch.service.IMutilpinsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caihao
 * @since 2019-07-02
 */
@RestController
@RequestMapping("/mutilpinsert")
public class MutilpinsertController {

    @Autowired
    private IMutilpinsertService iMutilpinsertService;

    @RequestMapping("/insert")
    public Map insert(){
        iMutilpinsertService.insertData();
        return new HashMap();
    }

    @RequestMapping("/other")
    public Map other(){
        iMutilpinsertService.other();
        return new HashMap();
    }

    @RequestMapping("/otherBatch")
    public Map otherBatch(){
        iMutilpinsertService.otherBatch();
        return new HashMap();
    }



}
