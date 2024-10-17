package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class SimpleOrderDto {
    @NotNull
    private Long userId;
    @Min(1)
    private Integer page;
    @Min(1)
    private Integer pageSize;
    @NotNull
    private Boolean isDeliverer;
    @Range(min = 0,max=3)
    private Integer status;
}
