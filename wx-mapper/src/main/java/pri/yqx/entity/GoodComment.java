package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data

public class GoodComment {
    @TableId
    private Long commentId;

    private Long goodId;

    private Long userId;


    private String content;
    //父评论
    private Long fatherId;


    private Long replyUserId;

    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Integer version;
    @TableLogic
    private Integer isDeleted;

}
