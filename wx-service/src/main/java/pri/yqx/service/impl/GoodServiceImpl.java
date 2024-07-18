package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pri.yqx.dto.GoodDto;
import pri.yqx.entity.Category;
import pri.yqx.entity.Good;
import pri.yqx.entity.GoodCategory;
import pri.yqx.entity.User;
import pri.yqx.mapper.GoodMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.GoodCategoryService;
import pri.yqx.service.GoodService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private GoodCategoryService goodCategoryService;
    @Override
    public void saveGood(GoodDto goodDto) {
        Long count = userService.lambdaQuery().eq(User::getUserId, goodDto.getUserId()).count();
        if(count<1)
            throw new RuntimeException("不存在该userId");
        List<Category> list = categoryService.lambdaQuery().in(Category::getCategoryId, goodDto.getCategoryIds()).list();
        if(list.size()!=goodDto.getCategoryIds().size())
            throw new RuntimeException("categoryId有误");
        Good good = MyBeanUtils.copyProperties(goodDto, new Good());
        good.setGoodId(IdWorker.getId());
        save(good);
        List<GoodCategory> goodCategories = list.stream().map(category ->
                new GoodCategory().setGoodId(good.getGoodId())
                        .setCategoryId(category.getCategoryId()).setCategoryName(category.getCategoryName())
        ).collect(Collectors.toList());
        goodCategoryService.saveBatch(goodCategories);
    }
}
