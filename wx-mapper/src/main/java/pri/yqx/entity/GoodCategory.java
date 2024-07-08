package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
/*
* 商品分类联合表
* */
@Data
public class GoodCategory {
    @TableId
    private Long goodCategoryId;
    private Long goodId;
    private Long categoryId;

}
