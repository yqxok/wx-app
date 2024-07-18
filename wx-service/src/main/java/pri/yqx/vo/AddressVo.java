package pri.yqx.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddressVo {

    private String dormiName;
    private String school;
    private String zone;
    private Integer dormiNum;
    private String phoneNumber;
}
