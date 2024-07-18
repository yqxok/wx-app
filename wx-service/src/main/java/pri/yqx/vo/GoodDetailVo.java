package pri.yqx.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoodDetailVo {

    //商品描述
    private String html;
    //商品图片数组,字段为json
    private List<String> picUrls;
    private BigDecimal price;
    private Short status;
    private Integer browserTimes;

    private List<String> categories;
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
