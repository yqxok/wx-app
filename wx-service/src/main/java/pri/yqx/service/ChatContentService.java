package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.ChatContentDto;
import pri.yqx.entity.ChatContent;
import pri.yqx.vo.ChatContentCountVo;
import pri.yqx.vo.ChatContentVo;

import java.time.LocalDateTime;
import java.util.List;


public interface ChatContentService extends IService<ChatContent> {
    public void updateContentRead(List<Long> contentDtos);

    public List<ChatContentCountVo> getUnReadCount(Long userId);
//    public Integer getNumOfUnRead(Long userId);
    public ChatContentVo saveAndGetChatContentVo(ChatContentDto chatContentDto);
    public List<ChatContentVo> getChatContentList(Long theOhterId,Long userId, Long goodId, LocalDateTime dateTime);
    public void validateChatId(Long chatId);
    public void removeChatContent(Long goodId, Long userId);

}
