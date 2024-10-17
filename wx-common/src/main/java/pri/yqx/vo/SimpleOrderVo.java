package pri.yqx.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SimpleOrderVo {
    private Long orderId;
    //商品数量
    private Integer number;
    private BigDecimal totalPrice;

    //订单状态，0正在交易中，1交易完成,2取消订单中,3订单取消成功
    private Integer status;
    private Long goodId;

    private String html;
    private String picUrl;
    private Long userId;
    private String userName;
    private String profilePicUrl;


}
