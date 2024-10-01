package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.ChatContentDto;
import pri.yqx.entity.Category;
import pri.yqx.entity.ChatContent;
import pri.yqx.entity.User;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.mapper.ChatContentMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.ChatContentService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.ChatContentCountVo;
import pri.yqx.vo.ChatContentVo;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatContentServiceImpl extends ServiceImpl<ChatContentMapper, ChatContent> implements ChatContentService {
    @Resource
    private ChatContentMapper chatContentMapper;
    @Resource
    private UserService userService;
    @Override
    public void updateContentRead(List<Long> chatIds) {
        this.lambdaUpdate().set(ChatContent::getIsRead,true).in(ChatContent::getChatId,chatIds).update();
        ChatContent one = this.lambdaQuery().eq(ChatContent::getChatId, chatIds.get(0)).select(ChatContent::getReceiveUserId).one();
        this.userService.lambdaUpdate().eq(User::getUserId,one.getReceiveUserId())
                .setSql("no_read_num=no_read_num-"+chatIds.size()).update();
    }

    @Override
    public List<ChatContentCountVo> getUnReadCount(Long userId) {
        return chatContentMapper.countUnRead(userId);
    }

    @Override
    public Integer getNumOfUnRead(Long userId) {
        return chatContentMapper.noReadCount(userId);
    }

    @Override
    public ChatContentVo saveAndGetChatContentVo(ChatContentDto chatContentDto) {
        Long chatId= IdWorker.getId();
        ChatContent chatContent = MyBeanUtils.copyProperties(chatContentDto, new ChatContent()).setChatId(chatId);
        this.save(chatContent);
        this.userService.lambdaUpdate().eq(User::getUserId,chatContent.getReceiveUserId())
                .setSql("no_read_num = no_read_num+1").update();
        ChatContent chatContent1 = this.getById(chatId);
        return MyBeanUtils.copyProperties(chatContent1,new ChatContentVo());
    }

    @Override
    public List<ChatContentVo> getChatContentList(Long theOtherId, Long userId, Long goodId, LocalDateTime dateTime) {
        return chatContentMapper.getChatContentList(theOtherId, userId,goodId,dateTime);
    }

    @Override
    public void validateChatId(Long chatId) {
        Long count = this.lambdaQuery().eq(ChatContent::getChatId, chatId).count();
        if(count<1)
            throw new RuntimeException("该chatId不存在");
    }

    @Override
    public void removeChatContent(Long goodId, Long userId) {
        this.lambdaUpdate()
                .eq(ChatContent::getGoodId,goodId)
                .eq(ChatContent::getIsDeleted,0)
                .and(i -> i.eq(ChatContent::getReceiveUserId, userId)
                        .or().eq(ChatContent::getReceiveUserId, userId)).set(ChatContent::getReceiveUserDelete,userId).update();
        this.lambdaUpdate()
                .eq(ChatContent::getGoodId,goodId)
                .eq(ChatContent::getIsDeleted,0)
                .and(i -> i.eq(ChatContent::getSendUserId, userId)
                        .or().eq(ChatContent::getSendUserId, userId)).set(ChatContent::getSendUserDelete,userId).update();

    }
}