package pri.yqx.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @TableId
    private Long categoryId;
    private String categoryName;
    //自关联的id
    private Long pkId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    private Long createUser;
    private Boolean isDeleted;
}
