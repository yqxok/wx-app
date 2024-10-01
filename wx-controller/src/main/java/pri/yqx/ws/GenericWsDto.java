package pri.yqx.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class GenericWsDto {
    private String uri;
    private Object data;
}
