package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.CollectDto;
import pri.yqx.entity.Collect;
import pri.yqx.entity.Good;
import pri.yqx.exceptions.BusinessException;
import pri.yqx.mapper.CollectMapper;
import pri.yqx.service.CollectService;
import pri.yqx.service.GoodService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.CollectNumVo;
import pri.yqx.vo.CollectVo;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Resource
    private UserService userService;
    @Resource
    private GoodService goodService;
    @Resource
    private CollectMapper collectMapper;

    @Override
    public Boolean saveCollect(CollectDto collectDto) {
        userService.validateUserId(collectDto.getUserId());
        goodService.validateGoodId(collectDto.getGoodId());
        Long count = this.lambdaQuery().eq(Collect::getUserId, collectDto.getUserId())
                .eq(Collect::getGoodId, collectDto.getGoodId())
                .count();
        if(count>0)
            throw new RuntimeException("该用户已经收藏此商品");
        this.goodService.lambdaUpdate().eq(Good::getGoodId,collectDto.getGoodId())
                .setSql("collect_num=collect_num+1").update();
        Collect collect = MyBeanUtils.copyProperties(collectDto, new Collect());
        save(collect);
        return true;
    }

    @Override
    public Page<CollectVo> getCollectVoPage(Long userId, Integer page, Integer pageSize) {
        int index=(page-1)*pageSize;
        List<CollectVo> collectVo = collectMapper.getCollectVoList(userId, index, pageSize);
        Page<CollectVo> collectVoPage = new Page<>(page, pageSize);
        collectVoPage.setRecords(collectVo).setTotal(collectVo.size()).setCurrent(page);
        return collectVoPage;
    }

    @Override
    public CollectNumVo getCollectNum(Long userId, Long goodId) {

        Good one = goodService.lambdaQuery().eq(Good::getGoodId, goodId).select(Good::getCollectNum).one();
        Collect one1 = this.lambdaQuery().eq(Collect::getUserId, userId).eq(Collect::getGoodId, goodId).one();
        CollectNumVo collectNumVo = new CollectNumVo().setGoodId(goodId).setUserId(userId).setCollectNum(one.getCollectNum());
        collectNumVo.setIsCollected(one1 != null);
        return collectNumVo;
    }

    @Override
    public void validateCollectId(Long collectId) {
        Long count = this.lambdaQuery().eq(Collect::getCollectId, collectId).count();
        if(count<1)
            throw new BusinessException("该collectId无效");
    }

    @Override
    public Boolean deleteCollect(List<Long> goodIds, Long userId) {
        this.remove(new LambdaQueryWrapper<Collect>().eq(Collect::getUserId,userId).in(Collect::getGoodId,goodIds));
        this.goodService.lambdaUpdate().in(Good::getGoodId,goodIds)
                .setSql("collect_num=collect_num-1").update();
        return true;
    }
}
