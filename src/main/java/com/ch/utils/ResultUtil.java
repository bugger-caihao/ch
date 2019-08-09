package com.ch.utils;

import com.ch.enumTool.ErrorCodeAndMessage;

import java.util.List;

/**
 * @ClassName: ResultUtil
 * @Description: 封装返回值的操作方法
 * @Author: caihao
 * @Date: 2019/7/16 16:43
 */
public class ResultUtil {

    /**
     * @Description 返回数据用对象封装
     * @Author caihao
     * @Date 2019/7/16 17:23
     * @Param [object]
     * @Return com.ch.utils.JsonResult
     */
    public static JsonResult success(Object object){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setRows(object);
        return jsonResult;
    }


    public static JsonResult success(){
        return success(null);
    }


    /**
     * @Description 返回数据用list封装
     * @Author caihao
     * @Date 2019/7/16 17:24
     * @Param [list, total]
     * @Return com.ch.utils.JsonResult
     */
    public static JsonResult successList(List list, long total){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setRows(list);
        jsonResult.setTotal(total);
        return jsonResult;
    }


    public static JsonResult successList(){
        return successList(null, 0L);
    }

    public static JsonResult error(List error){
        return new JsonResult(error);
    }

}
