package com.ch.exception;

import com.ch.enumTool.ErrorCodeAndMessage;
import com.ch.utils.JsonResult;

import java.io.Serializable;

/**
 * @ClassName: StudentException
 * @Description: 统一异常捕获类
 * @Author: caihao
 * @Date: 2019/7/10 15:33
 */
public class StudentException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final ErrorCodeAndMessage error;

    public StudentException(ErrorCodeAndMessage error) {
        this.error = error;
    }

    public ErrorCodeAndMessage getErrorCodeAndMessage() {
        return error;
    }
}
