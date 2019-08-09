package com.ch.controller;


import com.ch.entity.UserInfo;
import com.ch.enumTool.ErrorCodeAndMessage;
import com.ch.exception.StudentException;
import com.ch.service.IUserInfoService;
import com.ch.utils.JsonResult;
import com.ch.utils.MD5Utils;
import com.ch.utils.ResultUtil;
import com.ch.utils.ToolUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caihao
 * @since 2019-07-12
 */
@Controller
@Slf4j
@Api(tags = {"用户操作接口"}) // value是不会在文档中显示的，没有什么意义，一般不用指定
public class UserInfoController {

    @Autowired
    private IUserInfoService iUserInfoService;

    //  欢迎页
    @ApiOperation(value = "欢迎请求controller", notes = "项目访问路径：http://localhost:8080/")
    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(){
        //  走后端跳转的目的在与走拦截器，验证session中有没有用户信息
        return "main";
    }

    //  注册请求，跳转到注册页面
    @GetMapping("/register")
    public String register(){
        //  要排除拦截器拦截注册请求
        return "register";
    }


    /**
     * @Description 登陆请求
     * @Author caihao
     * @Date 2019/7/16 14:53
     * @Param [user]
     * @Return com.ch.utils.JsonResult
     */
    @PostMapping("/doLogin")
    @ResponseBody
    @ApiOperation(value = "根据用户输入的账号密码来判断是否正确，并成功跳转", notes = "备注信息...")
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    //@ApiImplicitParam(paramType = "body", name = "user", value = "用户信息对象", required = true, dataType = "UserInfo")
    public JsonResult<UserInfo> doLogin(@RequestBody @ApiParam(name = "userInfo", value = "user对象", required = true) UserInfo user){
        JsonResult<UserInfo> jsonResult = new JsonResult<>();
        String userPassword = user.getUserPassword();
        if (userPassword != null) {
            user.setUserPassword(MD5Utils.MD5Encode(userPassword));
            UserInfo userInfo = iUserInfoService.login(user);
            if(userInfo != null){
                //  将用户信息添加到session中
                ToolUtil.setLoginUser(userInfo);

                jsonResult.setRows(userInfo);
            }
        }else {
            throw new StudentException(ErrorCodeAndMessage.User_password_is_empty);
        }
        return jsonResult;
    }

    /**
     * @Description 修改密码
     * @Author caihao
     * @Date 2019/7/17 15:03
     * @Param [map]
     * @Return com.ch.utils.JsonResult
     */
    @PostMapping("/modifyPw")
    @ResponseBody
    public JsonResult<UserInfo> modifyPw(@RequestParam Map map){
        //  从session中获取用户信息
        UserInfo loginUser = ToolUtil.getLoginUser();
        if(null != map.get("oldPassword")){
            loginUser.setUserPassword(MD5Utils.MD5Encode(map.get("oldPassword").toString()));
            UserInfo login = iUserInfoService.login(loginUser);
            if(login != null){
                //  原密码和账号匹配，更新密码
                loginUser.setUserPassword(MD5Utils.MD5Encode(map.get("newPassword").toString()));
                iUserInfoService.updateById(loginUser);
                //  修改密码后使session过期
                ToolUtil.setLoginUser(null);

            }else {
                //  原密码和账号不匹配
                throw new StudentException(ErrorCodeAndMessage.User_password_not_match);
            }
        }else {
            //  传进来的原密码为空
            throw new StudentException(ErrorCodeAndMessage.User_password_is_empty);
        }
        //  根据用户名和原密码去查询
        JsonResult jsonResult = new JsonResult();
        return jsonResult;

    }

    /**
     * @Description 注册账号的后台请求
     * @Author caihao
     * @Date 2019/7/17 17:37
     * @Param [user]
     * @Return com.ch.utils.JsonResult
     */
    @PostMapping("/doRegister")
    @ResponseBody
    public JsonResult<UserInfo> doRegister(UserInfo user){
        //  查看注册的账号是否已经存在
        List<UserInfo> list = iUserInfoService.selectAccount(user);
        if(list.size() > 0){
            //  注册的账号已经存在
            throw new StudentException(ErrorCodeAndMessage.User_account_is_exist);
        }else {
            //  将注册信息插入到用户表中
            iUserInfoService.insert(user);
        }
        return ResultUtil.success();
    }

    @GetMapping("/logout")
    @ResponseBody
    public JsonResult<UserInfo> logout(){
        String account = ToolUtil.getLoginUser().getUserName();
        ToolUtil.setLoginUser(null);
        log.info("账户："+ account + "已退出登陆");
        return ResultUtil.success();
    }
}
