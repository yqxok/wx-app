package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*
* 聊天记录
*/

@Data
@Accessors(chain = true)
public class ChatContent {
    @TableId
    private Long chatId;

    private Long SendUserId;

    private Long receiveUserId;

    private String content;
    private Boolean isRead;
    private Long goodId;
    //默认值为0,为userId表明被该用户删除
    private Long sendUserDelete;
    private Long receiveUserDelete;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Integer version;
    @TableLogic
    private Integer isDeleted;

}
