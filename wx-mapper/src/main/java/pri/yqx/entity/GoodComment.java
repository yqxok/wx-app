package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data

public class GoodComment {
    @TableId
    @NotNull
    private Long commentId;
    @NotNull
    private Long goodId;
    @NotNull
    private Long userId;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String content;
    //父评论
    private Long fatherId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @NotNull
    private Long replyUserId;
    @NotEmpty
    private String replyName;

}
