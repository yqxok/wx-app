package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.entity.Category;
import pri.yqx.entity.Collect;
import pri.yqx.entity.Good;
import pri.yqx.entity.User;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.mapper.CollectMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.CollectService;
import pri.yqx.service.GoodService;
import pri.yqx.service.UserService;

import javax.annotation.Resource;

@Service
@Transactional
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Resource
    private UserService userService;
    @Resource
    private GoodService goodService;
    @Override
    public void saveCollect(Collect collect) {
        Long count = userService.lambdaQuery().eq(User::getUserId, collect.getUserId()).count();
        if(count<1)
            throw new RuntimeException("不存在该用户");
        Long count1 = goodService.lambdaQuery().eq(Good::getGoodId, collect.getGoodId()).count();
        if(count1<1)
            throw new RuntimeException("不存在该商品id");
        save(collect);
    }
}
