package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/*
* 收藏
* */
@Data
public class Collect {
    @TableId
    private Long collectId;
    private Long userId;
    private Long goodId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
}

