package pri.yqx.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import pri.yqx.json.PicUrl;
import pri.yqx.json.PicUrlJsonHandler;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(autoResultMap = true)
public class Good {
    @TableId
    private Long goodId;
    //商品描述
    private String html;
    //商品图片数组,字段为json
    @TableField(typeHandler = PicUrlJsonHandler.class)
    private List<PicUrl> picUrls;
    private BigDecimal price;
    private Short status;
    private Integer browserTimes;
    private Integer goodNum;
    private Integer collectNum;
    //关联的用户id
    private Long userId;

    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Integer version;
    @TableLogic
    private Integer isDeleted;
}
