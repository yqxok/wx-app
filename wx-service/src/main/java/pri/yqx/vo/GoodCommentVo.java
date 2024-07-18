package pri.yqx.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodCommentVo {

    private String userName;

    private String content;

    private String replyName;

    private LocalDateTime createTime;

}
