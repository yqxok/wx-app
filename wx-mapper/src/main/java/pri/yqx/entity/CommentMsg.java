package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentMsg {
    @TableId
    private Long cmMsgId;
    private Long commentId;
    private Long goodId;
    private String content;
    private Boolean isRead;
    private Long senderId;
    private Long receiverId;
    private Short type;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableLogic
    private Boolean isDeleted;
}
