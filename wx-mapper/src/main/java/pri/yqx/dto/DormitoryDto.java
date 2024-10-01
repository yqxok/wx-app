package pri.yqx.dto;

import lombok.Data;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;

@Data
public class DormitoryDto {

    @NotEmpty(groups = Insert.class)
    private String dormiName;
    @NotEmpty(groups = Insert.class)
    private String school;
    @NotEmpty(groups = Insert.class)
    private String zone;
}
