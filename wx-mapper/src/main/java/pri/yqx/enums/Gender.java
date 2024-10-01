package pri.yqx.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum Gender implements IEnum<Short> {
    MALE((short)1),FEMALE((short)0);
    private Short gender;
    Gender(Short gender){
        this.gender=gender;
    }
    @Override
    public Short getValue() {
        return this.gender;
    }
}
