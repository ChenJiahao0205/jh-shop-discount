package pers.jhshop.discount.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import pers.jhshop.common.entity.BaseVo;

/**
 * <p>
 * 优惠券表VO
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CouponsVO", description = "优惠券表列表展示VO")
public class CouponsVO extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠券ID，主键")
    private Long id;

    @ApiModelProperty(value = "优惠券代码")
    private String code;

    @ApiModelProperty(value = "优惠券类型，1为折扣券，2为满减券")
    private Integer type;

    @ApiModelProperty(value = "优惠金额")
    private BigDecimal discount;

    @ApiModelProperty(value = "最小订单金额")
    private BigDecimal minOrder;

    @ApiModelProperty(value = "优惠券生效时间")
    private LocalDateTime validFrom;

    @ApiModelProperty(value = "优惠券过期时间")
    private LocalDateTime validUntil;

    @ApiModelProperty(value = "优惠券状态（0-未使用，1-已使用，2-过期）")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String description;

}
