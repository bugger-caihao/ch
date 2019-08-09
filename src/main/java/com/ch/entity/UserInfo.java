package com.ch.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caihao
 * @since 2019-07-12
 */
@Data
//@AllArgsConstructor  //  带全部参数的有参构造方法（不判断参数的值是为为null）
//@RequiredArgsConstructor
@ApiModel("用户信息类")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键(mybatis插入数据的时候，会根据字段类型的长度来产生一个值，我刚开始设置的是50长度，就产生了一个long类型的值，导致用integer接收不了报错)
     */
    @ApiModelProperty("用户主键")
    @TableId("user_id")
    private Long userId;

    /**
     * 登录名
     */
    @ApiModelProperty("用户姓名")
    @TableField("user_name")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    @TableField("user_password")
    private String userPassword;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private String createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private String modifyTime;


}
