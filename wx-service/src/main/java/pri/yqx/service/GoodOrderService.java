package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.entity.GoodOrder;

public interface GoodOrderService extends IService<GoodOrder> {


    void saveGoodOrder(GoodOrder goodOrder);
}
