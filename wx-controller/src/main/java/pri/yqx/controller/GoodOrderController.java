package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.entity.GoodOrder;
import pri.yqx.entity.User;
import pri.yqx.groups.Insert;
import pri.yqx.service.GoodOrderService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goodOrder")
@Slf4j
public class GoodOrderController {
    @Resource
    private GoodOrderService goodOrderService;
    @PostMapping    //保存订单
    public Result<String> saveGoodOrder(@RequestBody @Validated(Insert.class) GoodOrder goodOrder){

        log.info("goodOrder={}",goodOrder);
        goodOrderService.saveGoodOrder(goodOrder);
        return Result.success(null,"订单保存成功");
    }
    @GetMapping("/{userId}/{page}/{pageSize}") //获取商品列表
    public Result<Page<GoodOrder>> page(@PathVariable long userId, @PathVariable int page, @PathVariable int pageSize){
        log.info("userId={},page={},pageSize={}",userId,page,pageSize);
        Page<GoodOrder> orderPage = new Page<>(page,pageSize);
        orderPage=goodOrderService.lambdaQuery().
                eq(GoodOrder::getUserId,userId)
                .page(orderPage);
        return Result.success(orderPage,"订单列表查询成功");
    }

}
