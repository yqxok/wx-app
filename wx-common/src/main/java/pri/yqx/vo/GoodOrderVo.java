package pri.yqx.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GoodOrderVo {

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
    private LocalDateTime dealTime;
    private String receiver;
    private String phoneNumber;
    private LocalDateTime createTime;
}
