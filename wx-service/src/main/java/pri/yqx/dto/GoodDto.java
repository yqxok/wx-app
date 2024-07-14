package pri.yqx.dto;

import lombok.Data;
import pri.yqx.entity.Good;

import java.util.List;

@Data
public class GoodDto extends Good {
    private List<Long> categories;
}
