package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodDto {

    //商品描述
    @Length(min=20,message = "不能小于{min}字符")
    @NotEmpty(groups = Insert.class)
    private String html;
    //商品图片数组,字段为json
    @Size(min=1,message = "商品图片不能为空")
    @NotNull(groups = Insert.class)
    private List<String> picUrls;
    @NotNull(groups = Insert.class)
    private BigDecimal price;
    //关联的用户id
    @NotNull
    private Long userId;
    @NotNull(groups = Insert.class)
    private List<Long> categoryIds;

}
