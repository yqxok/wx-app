package pri.yqx.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class GoodCommentDto {

    @NotNull(groups = Insert.class)
    private Long goodId;
    @NotNull(groups = Insert.class)
    private Long userId;
//    @NotNull(groups = Insert.class)
//    private String userName;

//    @NotNull(groups = Insert.class)
//    private String profilePicUrl;
    @NotEmpty(groups = Insert.class)
    private String content;
    //父评论id可为空
    private Long fatherId;

    @NotNull(groups = Insert.class)
    private Long replyUserId;
//    @NotEmpty(groups = Insert.class)
//    private String replyName;



}
