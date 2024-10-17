package pri.yqx.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class OrderMsgDto {
    @NotNull(groups = Update.class)
    private Long orderMsgId;
    @NotNull
    private Long senderId;
    @NotNull
    private Long receiverId;
    @NotNull
    private Long orderId;
    @NotEmpty
    private String content;
    @NotNull
    @Range(min=0,max=3)
    private Integer status;
}
