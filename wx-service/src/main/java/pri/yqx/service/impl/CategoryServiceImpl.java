package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.internal.dynalink.support.Guards;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.entity.Category;
import pri.yqx.entity.Good;
import pri.yqx.entity.GoodCategory;
import pri.yqx.entity.User;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.GoodCategoryService;
import pri.yqx.service.GoodService;
import pri.yqx.service.UserService;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Transactional
public class CategoryServiceImpl  extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
    @Resource
    private UserService userService;
    @Resource
    private GoodCategoryService goodCategoryService;
    @Override
    public void saveCategory(Category category) {

        if(Objects.nonNull(category.getPkId())){
            Long count = lambdaQuery().eq(Category::getCategoryId, category.getPkId()).count();
            if(count<1)
                throw new RuntimeException("pkId找不到对应的categoryId");
        }
        Long count = userService.lambdaQuery().eq(User::getUserId, category.getCreateUser()).count();
        if(count<1)
            throw new RuntimeException("createUser无效");
        save(category);

    }

    @Override
    public void deleteById(Long id) {
        //是否存在该id
        if(Objects.isNull(getById(id)))
            throw new RuntimeException("不存在该categoryId");
        Long count = goodCategoryService.lambdaQuery().eq(GoodCategory::getCategoryId, id).count();
        if(count<1)
            throw new RuntimeException("该categoryId已被其他表关联,不能删除");
        Long count1 = lambdaQuery().eq(Category::getPkId, id).count();
        if(count1>0)
            throw new RuntimeException("该categoryId已被自关联,不能删除");

        removeById(id);
    }
}
