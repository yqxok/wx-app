package pri.yqx.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodOrderVo {
    private Long GoodOrderId;
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
    //商品封面图片
    private String picUrl;
    private BigDecimal price;

}
