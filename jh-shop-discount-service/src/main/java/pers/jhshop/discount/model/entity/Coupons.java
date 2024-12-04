package pers.jhshop.discount.model.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author ChenJiahao(wutiao)
 * @since 2024-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("discount_coupons")
@ApiModel(value = "Coupons对象", description = "优惠券表")
public class Coupons extends Model<Coupons> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠券ID，主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "优惠券代码")
    @TableField("CODE")
    private String code;

    @ApiModelProperty(value = "优惠券类型，1为折扣券，2为满减券")
    @TableField("TYPE")
    private Integer type;

    @ApiModelProperty(value = "优惠金额")
    @TableField("DISCOUNT")
    private BigDecimal discount;

    @ApiModelProperty(value = "最小订单金额")
    @TableField("MIN_ORDER")
    private BigDecimal minOrder;

    @ApiModelProperty(value = "优惠券生效时间")
    @TableField("VALID_FROM")
    private LocalDateTime validFrom;

    @ApiModelProperty(value = "优惠券过期时间")
    @TableField("VALID_UNTIL")
    private LocalDateTime validUntil;

    @ApiModelProperty(value = "优惠券状态（0-未使用，1-已使用，2-过期）")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty(value = "描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty(value = "生效标志(TRUE-生效, FALSE-失效)")
    @TableField("VALID_FLAG")
    private Boolean validFlag;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
