package com.ch.config;

import com.ch.utils.JsonResult;
import com.ch.utils.ResultUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: JsrValidAspect
 * @Description: JSR303 校验实体类抛出异常的切面
 * @Author: caihao
 * @Date: 2019/8/8 14:24
 */
//@Aspect
//@Component
public class JsrValidAspect {

    /**
     * @Description 指定切点
     * @Author caihao
     * @Date 2019/8/8 14:51
     * @Param [bindingResult]
     * @Return com.ch.utils.JsonResult
     */
    @Pointcut("execution(public * com.ch.controller.*.*(..))")
    public void pointCut(){

    }

    @Around(value = "pointCut()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        Object obj = null;

        try {
            obj = proceedingJoinPoint.proceed(); //可以加参数
            Object[] args = proceedingJoinPoint.getArgs();
            for (Object arg : args) {
                if(arg instanceof BindingResult){
                    if(((BindingResult) arg).hasErrors()){
                        List<ObjectError> errorList = ((BindingResult) arg).getAllErrors();
                        List<String> mesList = new ArrayList<String>();
                        for (int i = 0; i < errorList.size(); i++) {
                            mesList.add(errorList.get(i).getDefaultMessage());
                        }
                        obj = ResultUtil.error(mesList);
                    }
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }

}
