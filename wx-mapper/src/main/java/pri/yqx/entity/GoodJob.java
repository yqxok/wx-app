package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodJob {
    @TableId
    private Long gjId;
    private Long commentId;
    private Long userId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
}
