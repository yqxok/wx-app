package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChatContentDto {
    private Long chatId;
    @NotNull(groups = Insert.class)
    private Long sendUserId;
    @NotNull(groups = Insert.class)
    private Long receiveUserId;
    @NotEmpty(groups = Insert.class)
    private String content;
    private Boolean isRead;
    private Long goodId;


}
