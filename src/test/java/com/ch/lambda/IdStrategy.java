package com.ch.lambda;

import com.ch.entity.Student;

/**
 * @ClassName: IdStrategy
 * @Description: 过滤id＞3的学生
 * @Author: caihao
 * @Date: 2019/9/6 9:48
 */
public class IdStrategy implements StrategyInterface {
    @Override
    public boolean strategy(Student student) {
        return student.getStudentId() > 3;
    }
}
