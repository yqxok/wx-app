package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
/*
* 订单
* */
@Data
public class GoodOrder {
    @TableId
    private Long orderId;
    //商品数量
    private Integer number;
    private BigDecimal totalPrice;
    //发货地址
    private String sAddress;
    //收货地址
    private String rAddress;
    //订单状态，0未完成，1完成
    private Integer status;

    private Long goodId;

    private Long userId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Integer version;
    @TableLogic
    private Integer isDeleted;
}
