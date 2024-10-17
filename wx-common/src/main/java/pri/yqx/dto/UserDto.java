package pri.yqx.dto;

import lombok.Data;
import pri.yqx.enums.Gender;
import pri.yqx.groups.Update;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UserDto {
    @NotNull(groups = Update.class)
    private Long userId;

    private String userName;

    private String password;

    private String phoneNumber;

    private LocalDate birthday;

    private Gender gender;
    //个人简介
    private String bio;
    //头像url
    private String profilePicUrl;

}
