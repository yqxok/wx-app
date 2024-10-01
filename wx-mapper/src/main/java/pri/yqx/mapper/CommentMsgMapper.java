package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.CommentMsg;
import pri.yqx.vo.CmMsgCountVo;
import pri.yqx.vo.CommentMsgVo;

import java.util.List;

@Mapper
public interface CommentMsgMapper extends BaseMapper<CommentMsg> {
   public CommentMsgVo getCommentMsgVo(long cmMsgId);

   public CmMsgCountVo getCmMsgCountVo(Long userId);
   public List<CommentMsgVo> getCommentMsgVoList(long userId,int index,int pageSize);
}
