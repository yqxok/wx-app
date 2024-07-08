package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodComment {
    @TableId
    private Long commentId;
    private Long goodId;
    private Long userId;
    private String content;
    //子评论
    private Long sonId;
    //父评论
    private Long fatherId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
}
