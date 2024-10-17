package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import pri.yqx.groups.Isphone;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    private String code;
    @NotEmpty
    @Isphone
    private String phoneNumber;
    @NotEmpty
    @Range(min = 8,max=13)
    private String password;
}
