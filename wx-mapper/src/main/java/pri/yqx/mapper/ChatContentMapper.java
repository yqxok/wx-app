package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.ChatContent;
import pri.yqx.vo.ChatContentCountVo;
import pri.yqx.vo.ChatContentVo;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ChatContentMapper extends BaseMapper<ChatContent> {
    //查询userId与所有其他用户的最新一条聊天记录以及未读聊天记录数
    public List<ChatContentCountVo> countUnRead(Long userId);
    public List<ChatContentVo> getChatContentList(Long theOtherId,Long userId, Long goodId, LocalDateTime dateTime);
    public Integer noReadCount(Long userId);
}
