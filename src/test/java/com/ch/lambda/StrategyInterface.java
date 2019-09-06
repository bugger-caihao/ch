package com.ch.lambda;

import com.ch.entity.Student;

/**
 * @Description 策略接口
 * @FunctionalInterface 用来验证该接口是否是函数式接口（只有一个方法的接口）
 * @Author caihao
 * @Date 2019/9/6 8:48
 * @Param
 * @Return
 */
@FunctionalInterface
public interface StrategyInterface {
    /**
     * 按照策略来过滤
     */
    boolean strategy(Student student);
}
