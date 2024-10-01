package pri.yqx.vo;

import lombok.Data;
import pri.yqx.json.PicUrl;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ChatContentCountVo {
    private Integer noReadCount;
    private Long theOtherId;
    private String content;
    private LocalDateTime createTime;
    private String theOtherName;
    private String theOtherProfileImg;
    private Long goodId;
    private List<PicUrl> picUrls;
}
