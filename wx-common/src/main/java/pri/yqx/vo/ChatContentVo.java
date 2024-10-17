package pri.yqx.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ChatContentVo {
    private Long chatId;
    private Long SendUserId;
    private Long receiveUserId;
    private Long goodId;
    private String content;
    private Boolean isRead;
    private LocalDateTime createTime;
}
