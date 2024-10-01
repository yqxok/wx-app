package pri.yqx.json;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PicUrl {
    private String url;
    private Integer width;
    private Integer height;
}
