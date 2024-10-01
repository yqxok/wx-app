package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.GoodComment;
import pri.yqx.vo.GoodCommentVo;

import java.util.List;

@Mapper
public interface GoodCommentMapper extends BaseMapper<GoodComment> {
    public List<GoodCommentVo> getGoodCommentVoList(Long goodId,Long userId);
    public GoodCommentVo getGoodCommentVo(Long commentId);
}
