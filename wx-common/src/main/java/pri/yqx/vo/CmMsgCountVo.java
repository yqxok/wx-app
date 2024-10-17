package pri.yqx.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CmMsgCountVo {
    private Integer noReadCount;
    private Short type;
    private String userName;
    private LocalDateTime createTime;
//    public String getUserName(){
//        if(this.userName!=null)
//            return this.userName+" 回复了你";
//        else
//            return "暂无互动消息";
//    }
}
