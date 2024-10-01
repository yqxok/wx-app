package pri.yqx.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.Getter;

@Getter
public enum AccountType implements IEnum<String> {
    REGULAR("REGULAR"),SELLER("SELLER"),ADMIN("ADMIN");
    final private String sort;
    AccountType(String sort){
        this.sort=sort;
    }
    @Override
    public String getValue() {
        return this.sort;
    }
}
