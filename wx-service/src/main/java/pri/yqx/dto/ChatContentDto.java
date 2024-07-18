package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChatContentDto {

    @NotNull(groups = Insert.class)
    private Long SendUserId;
    @NotNull(groups = Insert.class)
    private Long receiveUserId;
    @NotEmpty(groups = Insert.class)
    private String content;

}
