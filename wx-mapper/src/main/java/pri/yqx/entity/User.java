package pri.yqx.entity;

import lombok.Data;
import pri.yqx.enums.AccountType;
import pri.yqx.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class User {
    //id
    private Long userId;
    private String userName;
    private String password;
    private String phoneNumber;
    private String name;
    private LocalDate birthday;
    private Gender gender;
    //个人简介
    private String bio;
    //头像url
    private String profilePicUrl;
    private LocalDateTime createTime;
    private LocalDateTime lastLogin;
    //宿舍id
    private Long dormitory;
    private Integer dormiNum;
    //用户类型，枚举值
    private AccountType accountType;
    private Boolean isDeleted;
}
