package com.ch.entity;

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
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderHead对象", description="")
public class OrderHead implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    @TableId("order_id")
    private Integer orderId;

    @ApiModelProperty(value = "订单状态")
    @TableField("order_status")
    private String orderStatus;

    @ApiModelProperty(value = "订单备注")
    @TableField("order_remark")
    private String orderRemark;


}
