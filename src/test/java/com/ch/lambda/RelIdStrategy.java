package com.ch.lambda;

import com.ch.entity.Student;

/**
 * @ClassName: RelIdStrategy
 * @Description: 过滤关联id在50以下的学生
 * @Author: caihao
 * @Date: 2019/9/6 9:50
 */
public class RelIdStrategy implements StrategyInterface {
    @Override
    public boolean strategy(Student student) {
        return student.getRelId() < 50;
    }
}
