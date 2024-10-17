package pri.yqx.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderMsgCountVo {
    private Integer noReadNum;
    private String content;
    private LocalDateTime createTime;
}
