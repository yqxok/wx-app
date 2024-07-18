package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {
    @NotNull(groups = Insert.class)
    private String categoryName;
    private Long pkId;//可以为空
    @NotNull(groups = Insert.class)
    private Long createUser;
}
