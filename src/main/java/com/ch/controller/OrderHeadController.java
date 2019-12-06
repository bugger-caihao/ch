package com.ch.controller;


import com.ch.dto.OrderDto;
import com.ch.utils.JsonResult;
import com.ch.utils.ResultUtil;
import org.springframework.web.bind.annotation.*;

 import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.ch.service.OrderHeadService;

/**
* <p>
 *  前端控制器
 * </p>
*
* @author caihao
* @since 2019-12-03
*/
@RestController
@Slf4j
@Api(value = "order_headCRUD接口")
@RequestMapping("/order")
public class OrderHeadController {
    @Autowired
    private OrderHeadService order_headService;


    @PostMapping("/commit")
    public JsonResult commit(@RequestBody OrderDto orderDto){
        JsonResult result = ResultUtil.success();

        return result;
    }

}
