package com.ch.dto;

import com.ch.entity.OrderList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName: OrderDto
 * @Description:
 * @Author: caihao
 * @Date: 2019/12/4 17:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="订单dto", description="")
public class OrderDto {

    @ApiModelProperty(value = "订单号")
    private Integer orderId;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "订单备注")
    private String orderRemark;

    @ApiModelProperty(value = "订单明细")
    private List<OrderList> list;

}
