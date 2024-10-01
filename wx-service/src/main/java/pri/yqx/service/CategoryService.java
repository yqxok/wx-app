package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.CategoryDto;
import pri.yqx.entity.Category;


public interface CategoryService extends IService<Category> {
    public void saveCategory(CategoryDto category);
    public void validateCategory(String categoryName);
    public void deleteById(String categoryName);
}
