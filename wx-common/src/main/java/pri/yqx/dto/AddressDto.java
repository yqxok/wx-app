package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import pri.yqx.groups.Insert;
import pri.yqx.groups.Isphone;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddressDto {
    @NotNull(groups = Update.class)
    private Long addressId;
    @NotNull
    private Long userId;
    @NotEmpty(groups = Insert.class)
    private String receiver;
    @NotEmpty(groups = Insert.class)
    private String dormiName;
    @NotEmpty(groups = Insert.class)
    private String school;
    private Boolean isDefault;
    @NotEmpty(groups = Insert.class)
    private String zone;
    @NotNull(groups = Insert.class)
    private Integer dormiNum;

    @Isphone(groups = Insert.class)
    @NotEmpty(groups = Insert.class)
    private String phoneNumber;
}
