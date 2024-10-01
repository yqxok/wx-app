package pri.yqx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.CommentMsgDto;
import pri.yqx.entity.CommentMsg;
import pri.yqx.entity.User;
import pri.yqx.json.PicUrl;
import pri.yqx.mapper.CommentMsgMapper;
import pri.yqx.service.CommentMsgService;
import pri.yqx.service.GoodCommentService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.CmMsgCountVo;
import pri.yqx.vo.CommentMsgVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CommentMsgServiceImpl extends ServiceImpl<CommentMsgMapper, CommentMsg> implements CommentMsgService {
    @Resource
    private UserService userService;
    @Resource
    private GoodCommentService goodCommentService;
    @Resource
    private CommentMsgMapper commentMsgMapper;
    @Override
    public CommentMsgVo saveCommentMsg(CommentMsgDto commentMsgDto) {
        if(Objects.equals(commentMsgDto.getReceiverId(), commentMsgDto.getSenderId()))
            throw new RuntimeException("receiverId与senderId相等");
        goodCommentService.validateCommentId(commentMsgDto.getCommentId());
        userService.validateUserId(commentMsgDto.getSenderId());
        userService.validateUserId(commentMsgDto.getReceiverId());
        CommentMsg commentMsg = MyBeanUtils.copyProperties(commentMsgDto, new CommentMsg());
        long id = IdWorker.getId();
        commentMsg.setCmMsgId(id);
        this.save(commentMsg);
        this.userService.lambdaUpdate().eq(User::getUserId,commentMsgDto.getReceiverId())
                .setSql("no_read_num = no_read_num+1").update();
        CommentMsgVo commentMsgVo = commentMsgMapper.getCommentMsgVo(id);
        String picUrl = commentMsgVo.getPicUrl();
        List<PicUrl> picUrls = JSON.parseArray(picUrl, PicUrl.class);
        commentMsgVo.setPicUrl(picUrls.get(0).getUrl());

        return commentMsgVo;
    }

    @Override
    public CmMsgCountVo getCmMsgCountVo(Long userId) {
        this.userService.validateUserId(userId);
        CmMsgCountVo cmMsgCountVo = commentMsgMapper.getCmMsgCountVo(userId);
        if(cmMsgCountVo==null){
            cmMsgCountVo=new CmMsgCountVo();
            cmMsgCountVo.setNoReadCount(0).setCreateTime(null).setUserName(null);
        }
        return cmMsgCountVo;
    }

    @Override
    public Page<CommentMsgVo> getCmMsgVoPage(Long userId, Integer page, Integer pageSize) {
        int index=(page-1)*pageSize;
        this.userService.validateUserId(userId);
        List<CommentMsgVo> commentMsgVo = commentMsgMapper.getCommentMsgVoList(userId, index, pageSize);
//        List<CommentMsgVo> commentMsgVo = commentMsgMapper.getCommentMsgVoList(userId,index,pageSize);
        commentMsgVo.forEach(item->{
            String picUrl = item.getPicUrl();
            List<PicUrl> picUrls = JSON.parseArray(picUrl, PicUrl.class);
            item.setPicUrl(picUrls.get(0).getUrl());
        });
        Page<CommentMsgVo> commentMsgVoPage = new Page<>();
        commentMsgVoPage.setRecords(commentMsgVo).setTotal(commentMsgVo.size()).setCurrent(page);
        return commentMsgVoPage;
    }

    @Override
    public void cmMsgRead(List<Long> cmMsgIds) {
        this.lambdaUpdate().set(CommentMsg::getIsRead,true).in(CommentMsg::getCmMsgId,cmMsgIds).update();
        CommentMsg one = this.lambdaQuery().eq(CommentMsg::getCmMsgId, cmMsgIds.get(0)).select(CommentMsg::getReceiverId).one();
        this.userService.lambdaUpdate().eq(User::getUserId,one.getReceiverId())
                .setSql("no_read_num=no_read_num-"+cmMsgIds.size()).update();
    }
}
