package com.ch.config;

import com.ch.entity.UserInfo;
import com.ch.utils.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MyIntegerceptor
 * @Description:
 * @Author: caihao
 * @Date: 2019/6/27 11:34
 */
//@Component
@Slf4j
public class MyIntegerceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //  获取session中用户信息
        UserInfo loginUser = ToolUtil.getLoginUser();
        if(loginUser != null){
            //  session中有用户信息
            return true;
        }else {
            if(ToolUtil.isAjaxRequest(request)){
                //  是ajax请求
                response.setHeader("session-status", "timeout");
            }else {
                response.sendRedirect("http://localhost:8080");

            }
        }
        return false;
    }
}
