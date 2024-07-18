package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotNull;

@Data
public class CollectDto {
    @NotNull(groups = Insert.class)
    private Long userId;
    @NotNull(groups = Insert.class)
    private Long goodId;
}
