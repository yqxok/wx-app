package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.Collect;
import pri.yqx.vo.CollectVo;

import java.util.List;

@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
    public List<CollectVo> getCollectVoList(Long userId,Integer index,Integer pageSize);
//    public CollectNumVo getCollectNum(Long userId, Long goodId);
}
