package pri.yqx.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import pri.yqx.json.PicUrl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class GoodVo {
    //商品描述,前20字
    private String goodId;
    private String html;
    //商品封面
    private List<PicUrl>  picUrls;
    private BigDecimal price;
    //关联的用户id
    private Long userId;
    private String profilePicUrl;
    private String userName;

}
