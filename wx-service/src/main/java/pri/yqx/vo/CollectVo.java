package pri.yqx.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CollectVo {

    private Long goodId;
    //商品描述
    private String html;
    private String picUrl;
    private BigDecimal price;
    private Short status;
    private LocalDateTime createTime;

}
