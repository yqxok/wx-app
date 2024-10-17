package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.CategoryDto;
import pri.yqx.entity.Category;
import pri.yqx.entity.GoodCategory;
import pri.yqx.entity.User;
import pri.yqx.exceptions.BusinessException;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.GoodCategoryService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;

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
    public void saveCategory(CategoryDto categoryDto) {
        if(Objects.nonNull(categoryDto.getPkId())){
            Long count = lambdaQuery().eq(Category::getCategoryName, categoryDto.getPkId()).count();
            if(count<1)
                throw new BusinessException("pkId找不到对应的category");
        }
        Long count1 = userService.lambdaQuery().eq(User::getUserId, categoryDto.getCreateUser()).count();
        if(count1<1)
            throw new BusinessException("createUser无效");
        save(MyBeanUtils.copyProperties(categoryDto,new Category()));

    }

    @Override
    public void validateCategory(String categoryName) {
        Long count = this.lambdaQuery().eq(Category::getCategoryName, categoryName).count();
        if(count<1)
            throw new BusinessException("该分类不存在");
    }

    @Override
    public void deleteById(String categoryName) {
        //是否存在该分类
        if(Objects.isNull(getById(categoryName)))
            throw new BusinessException("不存在该category");
        Long count = goodCategoryService.lambdaQuery().eq(GoodCategory::getCategoryName, categoryName).count();
        if(count<1)
            throw new BusinessException("该category已被其他表关联,不能删除");
        Long count1 = lambdaQuery().eq(Category::getPkId, categoryName).count();
        if(count1>0)
            throw new BusinessException("该category已被自关联,不能删除");
        removeById(categoryName);
    }
}
