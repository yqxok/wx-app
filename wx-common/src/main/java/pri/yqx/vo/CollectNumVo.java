package pri.yqx.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CollectNumVo {
    private Long goodId;
    private Long userId;
    private Integer collectNum;
    private Boolean isCollected;
}
