package pri.yqx.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoodVo {
    //商品描述,前20字
    private String html;
    //商品封面
    private String picUrl;
    private BigDecimal price;
    private Short status;
    //关联的用户id
    private Long userId;

}
