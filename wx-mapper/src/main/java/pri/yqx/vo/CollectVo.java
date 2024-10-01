package pri.yqx.vo;

import lombok.Data;
import pri.yqx.json.PicUrl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CollectVo {
    private Long collectId;
    private Long userId;
    private String profilePicUrl;
    private String userName;
    private Long goodId;
    //商品描述
    private String html;
    private List<PicUrl> picUrls;
    private BigDecimal price;
    private Short status;
    private LocalDateTime createTime;

}
