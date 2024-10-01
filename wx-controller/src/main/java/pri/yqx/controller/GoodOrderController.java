package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.GoodOrderDto;
import pri.yqx.dto.SimpleOrderDto;
import pri.yqx.entity.GoodOrder;
import pri.yqx.service.GoodOrderService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.GoodOrderVo;
import pri.yqx.vo.SimpleOrderVo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goodOrder")
@Slf4j
public class GoodOrderController {
    @Resource
    private GoodOrderService goodOrderService;
    @PostMapping    //保存订单
    public Result<Long> saveGoodOrder(@RequestBody GoodOrderDto goodOrderDto){
        log.info("goodOrderDto={}",goodOrderDto);
        Long orderId = goodOrderService.saveGoodOrder(goodOrderDto);

        return Result.success(orderId,"订单保存成功");
    }

    @PostMapping("/simpleOrders") //获取已卖出或已买到商品列表
    public Result<Page<SimpleOrderVo>> page(@RequestBody SimpleOrderDto sDto){
        log.info("userId={},page={},pageSize={}",
                sDto.getUserId(),sDto.getPage(),sDto.getPageSize());
        Page<SimpleOrderVo> simpleOrderVoPage = goodOrderService.getSimpleOrderVoPage(sDto);
        return Result.success(simpleOrderVoPage,"订单列表查询成功");

    }
    @GetMapping("/{orderId}")
    public Result<GoodOrderVo> getGoodOrderVo(@PathVariable Long orderId){
        GoodOrder goodOrder = this.goodOrderService.getById(orderId);
        GoodOrderVo goodOrderVo = MyBeanUtils.copyProperties(goodOrder, new GoodOrderVo());
        return Result.success(goodOrderVo,"订单查询成功");
    }
    @PutMapping("/status")
    public Result<String> updateOrderStatus(@RequestBody GoodOrderDto goodOrderDto){
        this.goodOrderService.updateStatus(goodOrderDto);
//        this.goodOrderService.lambdaUpdate().eq(GoodOrder::getOrderId,goodOrderDto.getOrderId())
//                .set(GoodOrder::getStatus,goodOrderDto.getStatus()).update();

        return Result.success(null,"订单状态修改成功");
    }

}
