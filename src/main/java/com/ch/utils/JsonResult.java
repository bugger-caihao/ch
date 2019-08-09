package com.ch.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: JsonResult
 * @Description: 返回
 * @Author: caihao
 * @Date: 2019/7/10 11:07
 */
@Data
//该注解是在返回类的数据为null的时候，返回的给前端的数据中去掉data的key，而不是显示data: null
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@ApiModel("统一的返回值")
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    //  请求成功返回码
    @ApiModelProperty("请求成功返回码")
    private static final String successCode = "0000";

    //  返回数据
    @ApiModelProperty("返回数据对象")
    private T rows;

    //  返回记录总数
    @ApiModelProperty("返回总记录数")
    private long total;

    //  返回码
    @ApiModelProperty("返回码")
    private String code;

    //  返回描述
    @ApiModelProperty("返回结果的描述信息")
    private String msg;

    @ApiModelProperty("JSR-303 校验的结果，错误信息")
    private List error;



    //  无参构造
    public JsonResult() {
        this.code = successCode;
        this.msg = "请求成功";
    }

    // 带参构造
    public JsonResult(String code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(T rows, String code, String msg) {
        this();
        this.rows = rows;
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(T rows) {
        this();
        this.rows = rows;
    }

    public JsonResult(T rows, long total) {
        this();
        this.rows = rows;
        this.total = total;
    }

    public JsonResult(List error) {
        this();
        this.error = error;
    }
}
