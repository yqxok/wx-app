package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*
* 聊天记录
*/

@Data
public class ChatContent {
    @TableId
    private Long chatId;
    @NotNull
    private Long SendUserId;
    @NotNull
    private Long receiveUserId;
    @NotEmpty
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime sendTime;

}
