package pri.yqx.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    private String code;
    private String phoneNumber;
    private String password;
}
