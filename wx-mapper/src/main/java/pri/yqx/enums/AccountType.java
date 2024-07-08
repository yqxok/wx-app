package pri.yqx.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum AccountType implements IEnum<String> {
    REGULAR("Regular"),SELLER("Seller"),ADMIN("Admin");
    private String sort;
    AccountType(String sort){
        this.sort=sort;
    }
    @Override
    public String getValue() {
        return this.sort;
    }
}
