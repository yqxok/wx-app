package pri.yqx.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GoodOrderDto {


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

    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;


}
