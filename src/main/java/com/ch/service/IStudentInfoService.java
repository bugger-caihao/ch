package com.ch.service;

import com.ch.entity.StudentInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caihao
 * @since 2019-07-10
 */
public interface IStudentInfoService extends IService<StudentInfo> {

    StudentInfo selectById(StudentInfo studentInfo);
}
