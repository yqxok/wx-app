package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;
import pri.yqx.enums.AccountType;
import pri.yqx.enums.Gender;


import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
public class User {
    @TableId
    private Long userId;
    private String openId;
    private String sessionKey;
    private String userName;

    private String password;

    private String phoneNumber;

    private LocalDate birthday;

    private Gender gender;
    //个人简介
    private String bio;
    //头像url
    private String profilePicUrl;

    private LocalDateTime lastLogin;
    private Integer noReadNum;
    //用户类型，枚举值
    private AccountType accountType;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Integer version;
    @TableLogic
    private Integer isDeleted;
}
