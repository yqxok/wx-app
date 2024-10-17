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
    //收货地址
    private String rAddress;
    //订单状态，0正在交易中，1交易完成,2取消订单中,3订单取消成功
    private Integer status;

    private Long goodId;
    private Long delivererId;
    private Long receiverId;
    private String html;
    private String picUrl;
    private BigDecimal price;
    private String receiver;
    private String phoneNumber;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    private LocalDateTime dealTime;
    private Integer version;
    private Boolean delivererDeleted;
    private Boolean receiverDeleted;
}
