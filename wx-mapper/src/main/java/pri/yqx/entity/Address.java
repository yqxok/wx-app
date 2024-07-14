package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class Address {
    @TableId
    @NotNull
    private Long addressId;
    @NotNull
    private Long userId;
    @NotNull
    private Long dormiId;
    @Range(min=0)
    private Integer dormiNum;
    @Length(min=11,max=11,message = "电话号码错误")
    private String phoneNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
