package pri.yqx.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentMsgVo {
    private Long cmMsgId;
    private Long commentId;
    private Long goodId;
    private String picUrl;
    private Long userId;
    private String profilePicUrl;
    private String userName;
    private LocalDateTime createTime;
    private String content;
    private Short type;
    private Boolean isRead;
}
