package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.entity.Category;
import pri.yqx.entity.Good;
import pri.yqx.entity.GoodOrder;
import pri.yqx.entity.User;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.mapper.GoodOrderMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.GoodOrderService;
import pri.yqx.service.GoodService;
import pri.yqx.service.UserService;

import javax.annotation.Resource;

@Service
@Transactional
public class GoodOrderServiceImpl extends ServiceImpl<GoodOrderMapper, GoodOrder> implements GoodOrderService {
    @Resource
    private UserService userService;
    @Resource
    private GoodService goodService;
    @Override
    public void saveGoodOrder(GoodOrder goodOrder) {
        Long count = userService.lambdaQuery()
                .eq(User::getUserId, goodOrder.getUserId()).count();
        if(count<1)
            throw new RuntimeException("没有该userId");
        Long count1 = goodService.lambdaQuery().eq(Good::getGoodId, goodOrder.getGoodId()).count();
        if(count1<1)
            throw new RuntimeException("没有该goodId");
        save(goodOrder);
    }
}
