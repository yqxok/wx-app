package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.Category;
import pri.yqx.vo.CollectVo;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
