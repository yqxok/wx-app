package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GoodOrderDto {

    @NotNull(groups = Update.class)
    private Long orderId;
    //商品数量
    @NotNull
    @Min(1)
    private Integer number;
    @NotNull
    @Min(0)
    private BigDecimal totalPrice;
    //收货地址
    @NotEmpty
    private String rAddress;
    //订单状态，0正在交易中，1交易完成,2取消订单中,3订单取消成功
    @NotNull
    @Range(min=0,max=3)
    private Integer status;
    @NotNull
    private Long goodId;
    @NotNull
    private Long delivererId;
    @NotNull
    private Long receiverId;
    @NotEmpty
    private String html;
    @URL
    @NotEmpty
    private String picUrl;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotNull(groups = Update.class)
    private LocalDateTime dealTime;
    @NotEmpty
    private String receiver;
    @NotEmpty
    private String phoneNumber;



}
