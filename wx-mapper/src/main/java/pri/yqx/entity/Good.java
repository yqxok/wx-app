package pri.yqx.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

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
    /*TODO*/
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> picUrls;
    private BigDecimal price;
    private Short status;
    private Integer browserTimes;
    //关联的用户id
    private Long userId;
    //关联的分类id
    private Long categoryId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Boolean isDeleted;
}
