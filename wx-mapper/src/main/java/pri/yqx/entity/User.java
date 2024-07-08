package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import pri.yqx.enums.AccountType;
import pri.yqx.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class User {
    @TableId
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
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    private LocalDateTime lastLogin;
    //宿舍id
    private Long dormitory;
    private Integer dormiNum;
    //用户类型，枚举值
    private AccountType accountType;
    private Boolean isDeleted;
}
