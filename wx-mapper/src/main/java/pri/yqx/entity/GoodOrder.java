package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/*
* 订单
* */
@Data
public class GoodOrder {
    @TableId
    @NotNull(groups =Update.class)
    private Long orderId;
    //商品数量
    private Integer number;
    private BigDecimal totalPrice;
    //发货地址
    private String sAddress;
    //收货地址
    private String rAddress;
    //订单状态，0未完成，1完成
    private Byte status;
    @NotNull(groups = Insert.class)
    private Long goodId;
    @NotNull(groups = Insert.class)
    private Long userId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    private Boolean isDeleted;
}
