package com.ch.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: PageHelperParams
 * @Description: PageHelper 的参数
 * @Author: caihao
 * @Date: 2019/8/5 21:28
 */
@Data
@ApiModel("PageHelper 分页的参数")
public class PageHelperParams {

    @ApiModelProperty("初始化加载哪一页，默认第一页")
    private Integer pageNumber = 1;

    @ApiModelProperty("每页显示的记录数，这里定义的是10条")
    private Integer pageSize = 10;
}
