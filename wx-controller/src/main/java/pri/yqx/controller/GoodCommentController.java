package pri.yqx.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.GoodCommentDto;
import pri.yqx.entity.GoodComment;
import pri.yqx.service.GoodCommentService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.GoodCommentVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/goodComment")
public class GoodCommentController {
    @Resource
    private GoodCommentService goodCommentService;
    @PostMapping
    public Result<GoodCommentVo> saveComment(@RequestBody GoodCommentDto goodCommentDto){


        GoodCommentVo goodCommentVo = goodCommentService.saveGoodComment(goodCommentDto);

        return Result.success(goodCommentVo,"评论发表成功");
    }
//    @GetMapping("/no/{goodId}/{page}/{pageSize}")
//    public Result<Page<GoodCommentVo>> page(@PathVariable Long goodId, @PathVariable int page,
//                                            @PathVariable int pageSize, Long fatherId){
//        Page<GoodComment> commentPage = new Page<>(page, pageSize);
//        commentPage=goodCommentService.lambdaQuery()
//                .eq(GoodComment::getGoodId,goodId)
//                .eq(fatherId!=null,GoodComment::getFatherId,fatherId)
//                .page(commentPage);
//        Page<GoodCommentVo> goodCommentVoPage = MyBeanUtils.copyPage(commentPage, GoodCommentVo::new);
//        return Result.success(goodCommentVoPage,"获取评论列表成功");
//    }
    @GetMapping("/no/{goodId}")
    public Result<List<GoodCommentVo>> commentList(@PathVariable Long goodId,Long userId){
        List<GoodCommentVo> commentVoList = goodCommentService.getCommentVoList(goodId,userId);
        return Result.success(commentVoList,"查询所有评论成功");
    }
}
