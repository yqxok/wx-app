package pri.yqx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.GoodOrderDto;
import pri.yqx.dto.SimpleOrderDto;
import pri.yqx.entity.GoodOrder;
import pri.yqx.vo.SimpleOrderVo;

public interface GoodOrderService extends IService<GoodOrder> {


    Long saveGoodOrder(GoodOrderDto goodOrderDto);
    Page<SimpleOrderVo> getSimpleOrderVoPage(SimpleOrderDto simpleOrderDto);
    public void validateOrderId(Long orderId);

    void updateStatus(GoodOrderDto goodOrderDto);

    public void removeOrder(String token, Long orderId);
}
