package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.GoodDto;
import pri.yqx.entity.Good;

public interface GoodService extends IService<Good> {
    void saveGood(GoodDto good);
}
