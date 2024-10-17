package pri.yqx.vo;

import lombok.Data;
import pri.yqx.enums.AccountType;
import pri.yqx.enums.Gender;


import java.time.LocalDate;

@Data
public class UserVo {
    private Long userId;

    private String userName;

    private String phoneNumber;

    private LocalDate birthday;
    private Gender gender;
    //个人简介
    private String bio;
    //头像url
    private String profilePicUrl;
    //用户类型，枚举值
    private AccountType accountType;

}
