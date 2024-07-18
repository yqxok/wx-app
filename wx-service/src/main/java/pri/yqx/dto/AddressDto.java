package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import pri.yqx.groups.Insert;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddressDto {
    @NotNull
    private Long userId;
    @NotNull(groups = Insert.class)
    private Long dormitoryId;
    @Range(min=0,groups = Insert.class)
    @NotNull(groups = Insert.class)
    private Integer dormiNum;
    @Length(min=11,max=11,message = "电话号码格式错误")
    @NotEmpty(groups = Insert.class)
    private String phoneNumber;
}
