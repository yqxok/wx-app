package pri.yqx.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ChatContentVo {
    private Long SendUserId;
    private Long receiveUserId;
    private String content;
    private LocalDateTime createTime;
}
