package pri.yqx.dto;


import lombok.Data;
import org.hibernate.validator.constraints.Range;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentMsgDto {
    @NotNull(groups = Update.class)
    private Long commentId;
    @NotNull
    private Long goodId;
    @NotEmpty
    private String content;
    @Range(min=0,max=1)
    @NotNull
    private Short type;
    @NotNull
    private Long senderId;
    @NotNull
    private Long receiverId;

}
