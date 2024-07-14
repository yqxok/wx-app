package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/*
* 收藏
* */
@Data
public class Collect {
    @TableId
    @NotNull(groups = Update.class)
    private Long collectId;
    @NotNull(groups = Insert.class)
    private Long userId;
    @NotNull(groups = Insert.class)
    private Long goodId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
}

