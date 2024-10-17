package pri.yqx.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserLoginMsgVo {
    private Long senderId;
    private Long receiverId;
    private String msg;
}
