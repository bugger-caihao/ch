package com.ch.exception;

import com.ch.enumTool.ErrorCodeAndMessage;
import com.ch.utils.JsonResult;
import com.ch.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ExceptionHandler
 * @Description: 统一异常处理类
 * @Author: caihao
 * @Date: 2019/7/10 15:58
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {StudentException.class})
    @ResponseBody
    public JsonResult handleStudentException(HttpServletRequest request, StudentException ex){
        JsonResult jsonResult;
        log.error("StudentException code:{}, msg{}", ex.getErrorCodeAndMessage().getCode(), ex.getErrorCodeAndMessage().getMsg());
        jsonResult = new JsonResult(ex.getErrorCodeAndMessage().getCode(), ex.getErrorCodeAndMessage().getMsg());
        return jsonResult;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public JsonResult handleException(HttpServletRequest request, Exception ex){
        JsonResult jsonResult = null;
        log.error("Exception error:{}", ex);
        if(ex instanceof BindException){
            List errList = new ArrayList();
            BindException bindException = (BindException)ex;
            List<ObjectError> errors = bindException.getAllErrors();
            for (ObjectError error : errors) {
                errList.add(error.getDefaultMessage());
            }
            jsonResult = ResultUtil.error(errList);
            jsonResult.setCode("0007");
            jsonResult.setMsg("实体参数校验不通过");

        }else {

            jsonResult = new JsonResult(ErrorCodeAndMessage.Network_error.getCode(), ErrorCodeAndMessage.Network_error.getMsg());
        }

        return jsonResult;
    }

}
