package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Dormitory {
    @TableId
    private Long dormitoryId;
    private String dormiName;
}
