package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
* 商品分类联合表
* */
@Data
@Accessors(chain = true)
public class GoodCategory {
    @TableId
    @NotNull(groups = Update.class)
    private Long goodCategoryId;
    @NotNull(groups = Insert.class)
    private Long goodId;
    @NotNull(groups = Insert.class)
    private Long categoryId;
    @NotEmpty
    private String categoryName;
}
