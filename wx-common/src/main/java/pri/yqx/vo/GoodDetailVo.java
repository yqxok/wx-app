package pri.yqx.vo;

import lombok.Data;
import pri.yqx.json.PicUrl;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodDetailVo {
    private String goodId;
    private String html;
    //商品封面
    private List<PicUrl> picUrls;
    private BigDecimal price;
    private Short status;
    private Integer goodNum;
    private Integer collectNum;
    //关联的用户id
    private Long userId;
    private String profilePicUrl;
    private String userName;

}
