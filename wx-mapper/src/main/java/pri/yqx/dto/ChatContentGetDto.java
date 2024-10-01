package pri.yqx.dto;

import lombok.Data;

@Data
public class ChatContentGetDto {
    private Long userId;
    private Long theOtherId;
    private Long goodId;
    private Long timeStamp;
}
