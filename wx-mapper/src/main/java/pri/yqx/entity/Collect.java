package pri.yqx.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Collect {
    private Long collectId;
    private Long userId;
    private Long goodId;
    private LocalDateTime createTime;

}
