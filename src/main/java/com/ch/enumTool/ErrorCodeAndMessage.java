package com.ch.enumTool;

import lombok.Data;

/**
 * @Description 错误代码和错误信息的枚举类
 * @Author caihao
 * @Date 2019/7/10 11:29
 * @Param
 * @Return
 */

public enum ErrorCodeAndMessage {

    Student_id_not_exist("0001","学号不存在"),
    student_id_insufficient("0002","学号长度不足"),
    Student_id_is_empty("0003","学号为空"),
    User_password_not_match("0004", "原密码不正确"),
    User_password_is_empty("0005", "原密码为空"),
    User_account_is_exist("0006", "账号已存在"),
    Network_error("9999","网络错误，请重试"),
    Entity_valid_err("0007", "实体参数校验不通过");



    //  代码
    private String code;
    //  错误信息
    private String msg;

    ErrorCodeAndMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
