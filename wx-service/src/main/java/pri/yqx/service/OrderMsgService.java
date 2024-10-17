package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.OrderMsgDto;
import pri.yqx.entity.OrderMsg;
import pri.yqx.vo.OrderMsgCountVo;
import pri.yqx.vo.OrderMsgVo;

import java.util.List;

public interface OrderMsgService extends IService<OrderMsg> {
    public OrderMsgVo getOrderMsgVo(Long orderMsgId);
    public List<OrderMsgVo> getOrderMsgVoList(Long userId);
    public Long saveOrderMsg(OrderMsgDto orderMsgDto);
    public OrderMsgCountVo getOrderMsgCountVo(Long userId);
    public void validateOrderMsgId(Long orderMsgId);
    public void updateIsReadStatus(List<Long> orderMsgIds);
}
