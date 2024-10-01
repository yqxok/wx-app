package pri.yqx.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderMsgDto {
    private Long orderMsgId;
    private Long senderId;
    private Long receiverId;
    private Long orderId;
    private String content;
    private Integer status;
}
