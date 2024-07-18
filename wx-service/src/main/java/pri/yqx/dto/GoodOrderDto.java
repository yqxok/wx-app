package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GoodOrderDto {

    //商品数量
    @NotNull(groups = Insert.class)
    @Range(min=1)
    private Integer number;
    //发货地址
    @NotEmpty(groups = Insert.class)
    private String sAddress;
    //收货地址
    @NotEmpty(groups = Insert.class)
    private String rAddress;

    @NotNull(groups = Insert.class)
    private Long goodId;
    @NotNull(groups = Insert.class)
    private Long userId;

}
