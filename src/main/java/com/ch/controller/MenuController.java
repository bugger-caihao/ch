package com.ch.controller;


import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

 import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.ch.utils.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import com.github.pagehelper.PageInfo;
import com.ch.service.MenuService;
import com.ch.dto.MenuDto;
import com.ch.entity.Menu;

import javax.validation.Valid;

/**
* <p>
 *  前端控制器
 * </p>
*
* @author caihao
* @since 2019-08-06
*/
@RestController
@Slf4j
@Api(tags = {"menuCRUD接口"})
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //  当指定了请求的方式的时候，swagger2才会生成对应的请求的方法文档，如果没有指定请求的方式，则会默认的生成7种
    //  GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH 7种请求方式
    @ApiOperation(value = "分页查询 menu 列表",notes="")
    //@ApiImplicitParam(name = "menuDto", value = "menu 分页查询实体中的参数")
    @PostMapping("/queryAll")
    public JsonResult<List<Menu>> queryAll(MenuDto menuDto){

        PageInfo<Menu> pageInfo = menuService.queryAll(menuDto);
        return ResultUtil.successList(pageInfo.getList(), pageInfo.getTotal());
    }


    @GetMapping("/findParentMenu")
    @ApiOperation(value = "分页查询父级菜单集合",notes="")
    public JsonResult<List<Menu>> findParentMenu(){
        List<Menu> list = menuService.findParentMenu();
        return ResultUtil.success(list);
    }


    @GetMapping("/findSonMenu")
    public JsonResult<List<Menu>> findSonMenu(Menu menu){
        List<Menu> list = menuService.findSonMenu(menu);
        return ResultUtil.success(list);
    }


    @GetMapping("/test")
    public Map errorQuerry(@Valid MenuDto menuDto){
        Map map = new HashMap();


            PageInfo<Menu> pageInfo = menuService.queryAll(menuDto);
            map.put("success", true);
            map.put("rows", pageInfo.getList());
            map.put("total", pageInfo.getTotal());

        return map;
    }

}
