package pri.yqx.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChatContentGetDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long theOtherId;
    @NotNull
    private Long goodId;

    private Long timeStamp;
}
