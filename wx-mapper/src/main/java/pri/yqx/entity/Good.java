package pri.yqx.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName(autoResultMap = true)
public class Good {
    @TableId
    @NotNull(groups = Update.class)
    private Long goodId;
    //商品描述
    @Length(min=20,message = "不能小于{min}字符",groups = Insert.class)
    private String html;
    //商品图片数组,字段为json
    @TableField(typeHandler = FastjsonTypeHandler.class)
    @Size(min=1,message = "商品图片不能为空",groups = Insert.class)
    private List<String> picUrls;
    private BigDecimal price;
    private Short status;
    private Integer browserTimes;
    //关联的用户id
    @NotNull(groups = Insert.class)
    private Long userId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Boolean isDeleted;
}
