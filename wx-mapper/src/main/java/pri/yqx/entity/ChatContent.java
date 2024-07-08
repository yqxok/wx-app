package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/*
* 聊天记录
*/

@Data
public class ChatContent {
    @TableId
    private Long chatId;
    private Long SendUserId;
    private Long receiveUserId;
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime sendTime;

}
