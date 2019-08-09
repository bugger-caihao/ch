package com.ch.service.impl;

import com.ch.entity.StudentInfo;
import com.ch.mapper.StudentInfoMapper;
import com.ch.service.IStudentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caihao
 * @since 2019-07-10
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {

    @Autowired
    private StudentInfoMapper mapper;

    @Override
    public StudentInfo selectById(StudentInfo studentInfo) {
        return mapper.selectById(studentInfo.getStudentId());
    }
}
