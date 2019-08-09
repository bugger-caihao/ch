package com.ch.service.impl;

import com.ch.entity.Student;
import com.ch.mapper.StudentMapper;
import com.ch.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caihao
 * @since 2019-08-05
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
