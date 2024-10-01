package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotNull;

@Data
public class CollectDto {
    private Long collectId;
    private Long userId;
    private Long goodId;

}
