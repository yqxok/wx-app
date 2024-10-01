package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.OrderMsg;
import pri.yqx.vo.OrderMsgCountVo;
import pri.yqx.vo.OrderMsgVo;

import java.util.List;

@Mapper
public interface OrderMsgMapper extends BaseMapper<OrderMsg> {
    public OrderMsgVo getOrderMsgVo(Long orderMsgId);
    public List<OrderMsgVo> getOrderMsgVoList(Long userId);
    public OrderMsgCountVo getOrderMsgCountVo(Long userId);
}
