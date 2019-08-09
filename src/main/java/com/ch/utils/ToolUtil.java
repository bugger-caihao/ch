package com.ch.utils;

import com.ch.entity.UserInfo;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: ToolUtil
 * @Description: 操作session工具类
 * @Author: caihao
 * @Date: 2019/7/3 9:08
 */
public class ToolUtil {


    /**
     * @Description 获取session中登陆信息
     * @Author caihao
     * @Date 2019/7/3 13:27
     * @Param []
     * @Return java.lang.String
     */
    public static UserInfo getLoginUser(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        return user;
    }


    /**
     * @Description 将登陆信息设置到session中
     * @Author caihao
     * @Date 2019/7/3 13:30
     * @Param [loginUser]
     * @Return void
     */
    public static void setLoginUser(UserInfo loginUser){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute("user", loginUser);
    }


    /**
     * @Description 判断是不是ajax请求
     *              判断请求头中的"x-requested-with"对应的值是否为"XMLHttpRequest"，来确定是不是ajax请求
     * @Author caihao
     * @Date 2019/7/12 9:59
     * @Param [request]
     * @Return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
        String requestType = request.getHeader("x-requested-with");
        if(requestType != null && "XMLHttpRequest".equals(requestType)){
            return true;
        }else {
            return false;
        }
    }
}
