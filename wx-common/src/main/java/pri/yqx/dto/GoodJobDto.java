package pri.yqx.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class GoodJobDto {
    @NotNull
    private Long gjId;
    @NotNull
    private Long commentId;
    @NotNull
    private Long userId;
}
