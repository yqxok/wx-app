package pri.yqx.controller;

import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.service.OrderMsgService;
import pri.yqx.vo.OrderMsgCountVo;
import pri.yqx.vo.OrderMsgVo;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orderMsg")
public class OrderMsgController {
    @Resource
    private OrderMsgService orderMsgService;

    @GetMapping("/{userId}")
    public Result<List<OrderMsgVo>> getOrderMsgVos(@PathVariable Long userId){
        List<OrderMsgVo> orderMsgVoList = orderMsgService.getOrderMsgVoList(userId);
        return Result.success(orderMsgVoList,"订单消息查询成功");
    }
    @GetMapping("/noRead/{userId}")
    public Result<OrderMsgCountVo> getOrderMsgCountVo(@PathVariable Long userId){
        OrderMsgCountVo orderMsgCountVo = orderMsgService.getOrderMsgCountVo(userId);
        if(orderMsgCountVo==null){
            orderMsgCountVo=new OrderMsgCountVo();
            orderMsgCountVo.setContent("暂无订单消息");
            orderMsgCountVo.setNoReadNum(0);
            orderMsgCountVo.setCreateTime(null);
        }
        return Result.success(orderMsgCountVo,"未读订单数查询成功");
    }
    @PutMapping("/noRead")
    public Result<String> alreadyReadOrderMsg(@RequestBody List<Long> orderMsgIds){
        orderMsgService.updateIsReadStatus(orderMsgIds);
        return Result.success(null,"已读状态修改成功");
    }
}
