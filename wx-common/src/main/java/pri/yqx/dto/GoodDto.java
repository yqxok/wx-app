package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;
import pri.yqx.json.PicUrl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodDto {
    @NotNull(groups = Update.class)
    private Long goodId;
    //商品描述
    @Length(min=10,message = "不能小于{min}个字符")
    @NotEmpty
    private String html;
    //商品图片数组,字段为json
    @Size(min=1,message = "商品图片不能为空")
    @NotNull(groups = Insert.class)
    private List<PicUrl> picUrls;
    @NotNull(groups = Insert.class)
    private BigDecimal price;
    @NotNull(groups = Insert.class)
    private Integer goodNum;
    //关联的用户id
    @NotNull(groups = Insert.class)
    @Null(groups = Update.class)
    private Long userId;
    @NotNull(groups = Insert.class)
    private List<String> categories;

}
