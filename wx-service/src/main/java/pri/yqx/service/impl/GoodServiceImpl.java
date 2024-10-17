package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.GoodDto;
import pri.yqx.entity.*;
import pri.yqx.exceptions.BusinessException;
import pri.yqx.mapper.GoodMapper;
import pri.yqx.service.*;
import pri.yqx.util.JwtUtil;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.GoodDetailVo;
import pri.yqx.vo.GoodVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@Lazy
@Slf4j
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private GoodCategoryService goodCategoryService;


    @Resource
    private GoodCommentService goodCommentService;

    @Resource
    private GoodMapper goodMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void validateGoodId(Long goodId) {
        Long count = this.lambdaQuery().eq(Good::getGoodId, goodId).count();
        if(count<1)
            throw new BusinessException("该商品id不存在");
    }

    @Override
    public Long saveGood(GoodDto goodDto) {
        userService.validateUserId(goodDto.getUserId());
        List<Category> list = categoryService.lambdaQuery().in(Category::getCategoryName, goodDto.getCategories()).list();
        if(list.size()!=goodDto.getCategories().size())
            throw new RuntimeException("category有误");
        Good good = MyBeanUtils.copyProperties(goodDto, new Good());
        long id = IdWorker.getId();
        good.setGoodId(id);
        save(good);
        List<GoodCategory> goodCategories = list.stream().map(category ->
                new GoodCategory().setGoodId(good.getGoodId())
                        .setCategoryName(category.getCategoryName()).setLevel(category.getLevel())
        ).collect(Collectors.toList());
        goodCategoryService.saveBatch(goodCategories);
        return id;
    }

    @Override
    public Page<GoodVo> pageGoodVo(int page, int pageSize,String categoryName) {
//        int index= (page-1)*pageSize;
//        List<GoodVo> goodVos = goodMapper.selectGoodVo(index, pageSize, categoryName);
//        Page<GoodVo> goodVoPage = new Page<>(page, pageSize);
//        goodVoPage.setRecords(goodVos)
//                .setTotal(goodVos.size());
//        return goodVoPage;

        int index= (page-1)*pageSize;
        List<Long> ids = goodMapper.selectGoodId(index, pageSize, categoryName);

        List goodVos =redisTemplate.opsForValue()
                .multiGet(ids.stream().map(i -> "goodVo:" + i).collect(Collectors.toList()));
        log.debug("redis查询{}",goodVos);
        goodVos=(List)goodVos.stream().filter(Objects::nonNull).collect(Collectors.toList());
        List<Long> hitIds =(List<Long>) goodVos.stream().map(i -> ((GoodVo) i).getGoodId()).collect(Collectors.toList());
        List<Long> noHitIds=ids.stream().filter(i->!hitIds.contains(i)).collect(Collectors.toList());
        List<GoodVo> noHitGoodVos=null;
        if(!noHitIds.isEmpty()){
            noHitGoodVos = goodMapper.getGoodVos(noHitIds);
            Map<String, GoodVo> collect = noHitGoodVos.stream().collect(Collectors.toMap(i -> "goodVo:" + i.getGoodId(), i -> i));
            redisTemplate.opsForValue().multiSet(collect);
        }

        goodVos.addAll(noHitGoodVos==null?new ArrayList():noHitGoodVos);

        Page<GoodVo> goodVoPage = new Page<>(page, pageSize);
        goodVoPage.setRecords(goodVos)
                .setTotal(goodVos.size());
        return goodVoPage;

    }

    @Override
    public List<GoodVo> listGoodVoById(Long userId,Short status) {
        return goodMapper.getGoodVoList(userId,status);
    }

    @Override
    public void deleteGoodById(Long goodId,String token) {
        Long userId = JwtUtil.getUserId(token);
        Good good = this.getById(goodId);
        if(!Objects.equals(good.getUserId(),userId))
            throw new RuntimeException("你没有权限删除该商品");
        this.goodCategoryService.remove(new LambdaQueryWrapper<GoodCategory>().eq(GoodCategory::getGoodId,goodId));
        this.removeById(goodId);
    }

    @Override
    public GoodDetailVo getGoodDetailVo(Long goodId) {
        this.validateGoodId(goodId);
        return goodMapper.getGoodDetailVo(goodId);
    }

    @Override
    public Long updateGood(GoodDto goodDto) {
        this.validateGoodId(goodDto.getGoodId());
        Good good = MyBeanUtils.copyProperties(goodDto, new Good());
        goodCategoryService.remove(new LambdaQueryWrapper<GoodCategory>().eq(GoodCategory::getGoodId,goodDto.getGoodId()));
        List<Category> list = categoryService.lambdaQuery().in(Category::getCategoryName, goodDto.getCategories()).list();
        if(list.size()!=goodDto.getCategories().size())
            throw new RuntimeException("category有误");
        List<GoodCategory> goodCategories = list.stream().map(category ->
                new GoodCategory().setGoodId(good.getGoodId())
                        .setCategoryName(category.getCategoryName()).setLevel(category.getLevel())
        ).collect(Collectors.toList());
        goodCategoryService.saveBatch(goodCategories);
        this.updateById(good);
        return goodDto.getGoodId();
    }
}
