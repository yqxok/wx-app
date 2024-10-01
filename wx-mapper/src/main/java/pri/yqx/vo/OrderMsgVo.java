package pri.yqx.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class OrderMsgVo {
    private Long orderMsgId;
    private Long orderId;
    private Long goodId;
    private Long receiverId;
    private Long senderId;
    private Integer status;
    private Boolean isRead;
    private String content;
    //收货人或发货人头像
    private String profilePicUrl;
    private String html;
    private String userName;
    private LocalDateTime createTime;
    private String picUrl;
}
