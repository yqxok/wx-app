package pri.yqx.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddressVo {

//    private String dormiName;
//    private String school;
//    private String zone;
    private Long addressId;
    private Integer dormiNum;
    private String dormiName;
    private String school;
    private String zone;
    private String phoneNumber;
    private String receiver;
    private Boolean isDefault;

}
