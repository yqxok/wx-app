package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class Category {
    @TableId
    @NotNull(groups = Update.class)
    private Long categoryId;
    @NotEmpty
    private String categoryName;
    //自关联的id,可以为空
    private Long pkId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @NotNull
    private Long createUser;
    private Boolean isDeleted;


}
