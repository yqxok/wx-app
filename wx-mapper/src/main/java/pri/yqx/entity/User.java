package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import pri.yqx.enums.AccountType;
import pri.yqx.enums.Gender;
import pri.yqx.groups.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Accessors(chain = true)

public class User {
    @TableId
    @NotNull(message = "userId不能为空",groups = Update.class)
    private Long userId;
    private String openId;
    private String sessionKey;
    private String userName;

    private String password;
    @Length(min=11,max = 11,groups = Update.class)
    private String phoneNumber;
    @Past(groups = Update.class)
    private LocalDate birthday;
    private Gender gender;
    //个人简介
    private String bio;
    //头像url
    private String profilePicUrl;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    private LocalDateTime lastLogin;
    //用户类型，枚举值
    private AccountType accountType;
    private Boolean isDeleted;
}
