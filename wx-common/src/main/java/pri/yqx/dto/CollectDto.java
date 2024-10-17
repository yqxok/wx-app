package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotNull;

@Data
public class CollectDto {
    @NotNull(groups = Update.class)
    private Long collectId;
    @NotNull(groups = Insert.class)
    private Long userId;
    @NotNull(groups = Insert.class)
    private Long goodId;

}
