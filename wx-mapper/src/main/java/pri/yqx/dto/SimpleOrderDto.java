package pri.yqx.dto;

import lombok.Data;

@Data
public class SimpleOrderDto {
    private Long userId;
    private Integer page;
    private Integer pageSize;
    private Boolean isDeliverer;
    private Integer status;
}
