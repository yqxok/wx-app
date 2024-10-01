package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.dto.SimpleOrderDto;
import pri.yqx.entity.Good;
import pri.yqx.entity.GoodOrder;
import pri.yqx.vo.SimpleOrderVo;

import java.util.List;

@Mapper
public interface GoodOrderMapper extends BaseMapper<GoodOrder> {
    //收货人或发货人订单消息
    public List<SimpleOrderVo> getSimpleOrderVos(SimpleOrderDto sDto);
    public Good getGoodByOrderId(Long orderId);
}
