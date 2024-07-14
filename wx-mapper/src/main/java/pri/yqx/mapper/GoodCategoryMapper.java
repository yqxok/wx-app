package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.Category;
import pri.yqx.entity.GoodCategory;

@Mapper
public interface GoodCategoryMapper extends BaseMapper<GoodCategory> {
}
