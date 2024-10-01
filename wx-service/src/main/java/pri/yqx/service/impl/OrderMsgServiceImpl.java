package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.OrderMsgDto;
import pri.yqx.entity.OrderMsg;
import pri.yqx.entity.User;
import pri.yqx.mapper.OrderMsgMapper;
import pri.yqx.mapper.UserMapper;
import pri.yqx.service.OrderMsgService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.OrderMsgCountVo;
import pri.yqx.vo.OrderMsgVo;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class OrderMsgServiceImpl extends ServiceImpl<OrderMsgMapper, OrderMsg> implements OrderMsgService {
    @Resource
    private OrderMsgMapper orderMsgMapper;
    @Resource
    private UserService userService;
    @Override
    public OrderMsgVo getOrderMsgVo(Long orderMsgId) {
        return orderMsgMapper.getOrderMsgVo(orderMsgId);
    }

    @Override
    public List<OrderMsgVo> getOrderMsgVoList(Long userId) {
        return orderMsgMapper.getOrderMsgVoList(userId);
    }

    @Override
    public Long saveOrderMsg(OrderMsgDto orderMsgDto) {
        OrderMsg orderMsg = MyBeanUtils.copyProperties(orderMsgDto, new OrderMsg());
        long orderMsgId = IdWorker.getId();
        orderMsg.setOrderMsgId(orderMsgId);

        save(orderMsg);
        this.userService.lambdaUpdate().eq(User::getUserId,orderMsgDto.getReceiverId())
                .setSql("no_read_num = no_read_num+1").update();
        return orderMsgId;
    }

    @Override
    public OrderMsgCountVo getOrderMsgCountVo(Long userId) {
        return orderMsgMapper.getOrderMsgCountVo(userId);
    }

    @Override
    public void validateOrderMsgId(Long orderMsgId) {
        Long count = this.lambdaQuery().eq(OrderMsg::getOrderMsgId, orderMsgId).count();
        if(count<1)
            throw new RuntimeException("该orderMsgId无效");
    }

    @Override
    public void updateIsReadStatus(List<Long> orderMsgIds) {
        this.lambdaUpdate().in(OrderMsg::getOrderMsgId,orderMsgIds).set(OrderMsg::getIsRead,true)
                .update();
        Long receiverId = this.getById(orderMsgIds.get(0)).getReceiverId();
        this.userService.lambdaUpdate().eq(User::getUserId,receiverId)
                .setSql("no_read_num=no_read_num-"+orderMsgIds.size()).update();
    }
}
