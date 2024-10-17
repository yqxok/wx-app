package pri.yqx.dto;


import lombok.Data;

import pri.yqx.groups.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class GoodCommentDto {
    @NotNull(groups = Update.class)
    private Long commentId;
    @NotNull
    private Long goodId;
    @NotNull
    private Long userId;

    @NotEmpty
    private String content;
    //父评论id可为空
    private Long fatherId;

    @NotNull
    private Long replyUserId;

}
