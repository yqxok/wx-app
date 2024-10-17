package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.Good;
import pri.yqx.vo.GoodDetailVo;
import pri.yqx.vo.GoodVo;

import java.util.List;

@Mapper
public interface GoodMapper extends BaseMapper<Good> {
    public List<Long> selectGoodId(int index, int size, String categoryName);
    public List<GoodVo> selectGoodVo(int index,int size,String categoryName);
    public GoodDetailVo getGoodDetailVo(Long goodId);
    public List<GoodVo> getGoodVoList(Long userId,Short status);

    public List<GoodVo> getGoodVos(List<Long> noHitIds);
}