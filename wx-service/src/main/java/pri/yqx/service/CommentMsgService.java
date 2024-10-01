package pri.yqx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.CommentMsgDto;
import pri.yqx.entity.CommentMsg;
import pri.yqx.vo.CmMsgCountVo;
import pri.yqx.vo.CommentMsgVo;

import java.util.List;

public interface CommentMsgService extends IService<CommentMsg> {
    public CommentMsgVo saveCommentMsg(CommentMsgDto commentMsgDto);

    public CmMsgCountVo getCmMsgCountVo(Long userId);

    public Page<CommentMsgVo> getCmMsgVoPage(Long userId, Integer page, Integer pageSize);

    public void cmMsgRead(List<Long> cmMsgIds);
}
