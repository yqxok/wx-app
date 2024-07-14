package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Dormitory {
    @TableId
    private Long dormitoryId;
    @NotEmpty
    private String dormiName;
    @NotEmpty
    private String school;
    @NotEmpty
    private String zone;
}
