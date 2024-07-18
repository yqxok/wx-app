package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class Category {
    @TableId
    private Long categoryId;

    private String categoryName;
    //自关联的id,可以为空
    private Long pkId;

    private Long createUser;

    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Integer version;
    @TableLogic
    private Integer isDeleted;
}
