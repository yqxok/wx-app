package pri.yqx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.ChatContentDto;
import pri.yqx.dto.ChatContentGetDto;
import pri.yqx.entity.ChatContent;
import pri.yqx.service.ChatContentService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.ChatContentCountVo;
import pri.yqx.vo.ChatContentVo;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatContent")
public class ChatContentController {
    @Resource
    private ChatContentService chatContentService;
    @PutMapping
    public Result<String> chatContentRead(@RequestBody List<Long> chatIds){
        chatContentService.updateContentRead(chatIds);
        return Result.success(null,"消息已读状态修改成功");
    }
    @GetMapping("/unRead/{userId}")
    public Result<List<ChatContentCountVo>> getNoReadCount(@PathVariable Long userId){
        List<ChatContentCountVo> unReadCounts = chatContentService.getUnReadCount(userId);
        return Result.success(unReadCounts,"未读消息数以及最新消息查询成功");
    }
    @PostMapping//获取聊天记录
    public Result<List<ChatContentVo>> getChatContetnList(@RequestBody ChatContentGetDto gDto){
        LocalDateTime localDateTime=gDto.getTimeStamp()==null?null:timestamToDatetime(gDto.getTimeStamp());
        List<ChatContentVo> chatContentList = this.chatContentService
                .getChatContentList(gDto.getTheOtherId(), gDto.getUserId(), gDto.getGoodId(), localDateTime);
        return Result.success(chatContentList,"聊天记录查询成功");

    }
    @GetMapping("/{userId}/unReadNum")
    public Result<Integer> unReadNum(@PathVariable Long userId){
        Integer numOfUnRead = chatContentService.getNumOfUnRead(userId);
        return Result.success(numOfUnRead,"用户未读消息查询成功");
    }
    @DeleteMapping("/{userId}/{goodId}")
    public Result<String> deleteGood(@PathVariable Long goodId,@PathVariable Long userId){
        chatContentService.removeChatContent(goodId,userId);

        return Result.success(null, "goodId为"+goodId+" 消息删除成功");
    }

    public LocalDateTime timestamToDatetime(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
