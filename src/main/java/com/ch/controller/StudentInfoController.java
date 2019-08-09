package com.ch.controller;


import com.ch.entity.StudentInfo;
import com.ch.enumTool.ErrorCodeAndMessage;
import com.ch.exception.StudentException;
import com.ch.service.IStudentInfoService;
import com.ch.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caihao
 * @since 2019-07-10
 */
@RestController
@RequestMapping("/student-info")
@Slf4j
public class StudentInfoController {

    @Autowired
    private IStudentInfoService iStudentInfoService;

    /**
     * @Description 保存学生信息
     * @Author caihao
     * @Date 2019/7/10 16:38
     * @Param [studentInfo]
     * @Return void
     */
    @PostMapping("/save")
    public void save(StudentInfo studentInfo){
        iStudentInfoService.save(studentInfo);
    }

    @PostMapping("selectById")
    public JsonResult<StudentInfo> selectById(StudentInfo studentInfo){
        try {
            log.info("get student information by id:{}", studentInfo.getStudentId());
            if(null == studentInfo.getStudentId()){
                throw new StudentException(ErrorCodeAndMessage.Student_id_is_empty);
            }
            if(8 != studentInfo.getStudentId().toString().length()){
                throw new StudentException(ErrorCodeAndMessage.student_id_insufficient);
            }

            StudentInfo student = iStudentInfoService.selectById(studentInfo);

            if(null == student){
                throw new StudentException(ErrorCodeAndMessage.Student_id_not_exist);
            }
            return new JsonResult<>(student);
        } catch (StudentException e) {
            if(e instanceof StudentException){
                throw e;
            }else {

                log.error("select by id error:{}", e);
                throw new StudentException(ErrorCodeAndMessage.Network_error);
            }
        }

    }


    @GetMapping("/test")
    public String test(HttpServletRequest request){
        return "123";
    }
}
