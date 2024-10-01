package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.entity.CommentMsg;
import pri.yqx.service.CommentMsgService;
import pri.yqx.vo.CmMsgCountVo;
import pri.yqx.vo.CommentMsgVo;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cmMsg")
public class commentMsgController {
    @Resource
    private CommentMsgService commentMsgService;
    @GetMapping("/{userId}/{page}/{pageSize}")
    public Result<Page<CommentMsgVo>> getCmMsgVoList(@PathVariable Long userId,
                                                     @PathVariable Integer page, @PathVariable Integer pageSize){
        Page<CommentMsgVo> cmMsgVoPage = commentMsgService.getCmMsgVoPage(userId, page, pageSize);

        return Result.success(cmMsgVoPage,"回复消息查询成功");
    }
    @GetMapping("/noRead/{userId}")
    public Result<CmMsgCountVo> getCmMsgCountVo(@PathVariable Long userId){
        CmMsgCountVo cmMsgCountVo = commentMsgService.getCmMsgCountVo(userId);
        return Result.success(cmMsgCountVo,"回复消息查询成功");
    }
    @PutMapping("/read")
    public Result<String> cmMsgRead(@RequestBody List<Long> cmMsgIds){
        commentMsgService.cmMsgRead(cmMsgIds);

        return Result.success(null,"回复消息已读状态修改成功");
    }
}
