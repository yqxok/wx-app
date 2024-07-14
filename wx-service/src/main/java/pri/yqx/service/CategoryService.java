package pri.yqx.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.Category;


public interface CategoryService extends IService<Category> {
  public void saveCategory(Category category);

   public void deleteById(Long id);
}
