package pri.yqx.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum Gender implements IEnum<String> {
    MALE("MALE"),FEMALE("FEMALE");
    private String gender;
    Gender(String gender){
        this.gender=gender;
    }
    @Override
    public String getValue() {
        return this.gender;
    }
}
