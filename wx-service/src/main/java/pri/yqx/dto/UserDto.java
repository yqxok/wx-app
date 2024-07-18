package pri.yqx.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pri.yqx.enums.Gender;
import pri.yqx.groups.Update;

import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class UserDto {
    private String userName;
    private String password;
    @Length(min=11,max = 11,groups = Update.class)
    private String phoneNumber;
    @Past
    private LocalDate birthday;
    private Gender gender;
    //个人简介
    private String bio;
    //头像url
    private String profilePicUrl;

}
