package com.ch.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author caihao
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "父级菜单编号")
    @TableField("parent_number")
    private Integer parentNumber;

    @ApiModelProperty(value = "父级菜单名称")
    @TableField("parent_name")
    private String parentName;

    @ApiModelProperty(value = "子级菜单编号(顺序)")
    @TableField("son_order")
    private Integer sonOrder;

    @ApiModelProperty(value = "子级菜单名称")
    @TableField("son_name")
    private String sonName;

    @ApiModelProperty(value = "子级菜单链接")
    @TableField("son_rel")
    private String sonRel;

    @ApiModelProperty(value = "菜单图标")
    @TableField("menu_icon")
    private String menuIcon;

    @ApiModelProperty(value = "是否可用(0：不可用；1：可用)")
    @TableField("enable")
    private Integer enable;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "创建人id")
    @TableField("create_id")
    private Integer createId;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "修改人id")
    @TableField("update_id")
    private Integer updateId;


}
