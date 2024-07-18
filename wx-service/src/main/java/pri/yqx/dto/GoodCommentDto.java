package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GoodCommentDto {

    @NotNull(groups = Insert.class)
    private Long goodId;
    @NotNull(groups = Insert.class)
    private Long userId;

    @NotEmpty(groups = Insert.class)
    private String content;
    //父评论,可为空
    private Long fatherId;

    @NotNull(groups = Insert.class)
    private Long replyUserId;
    @NotEmpty(groups = Insert.class)
    private String replyName;
}
