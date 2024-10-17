package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChatContentDto {
    @NotNull(groups = Update.class)
    private Long chatId;
    @NotNull(groups = Insert.class)
    private Long sendUserId;
    @NotNull(groups = Insert.class)
    private Long receiveUserId;
    @NotEmpty(groups = Insert.class)
    private String content;
    private Boolean isRead;
    @NotNull
    private Long goodId;

}
