package pri.yqx.dto;


import lombok.Data;

@Data
public class CommentMsgDto {

    private Long commentId;
    private Long goodId;
    private String content;
    private Short type;
    private Long senderId;
    private Long receiverId;

}
