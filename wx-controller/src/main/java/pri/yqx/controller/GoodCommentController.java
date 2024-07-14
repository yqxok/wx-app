package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.entity.GoodComment;
import pri.yqx.service.GoodCommentService;

import javax.annotation.Resource;


@RestController
@RequestMapping("/goodComment")
public class GoodCommentController {
    @Resource
    private GoodCommentService goodCommentService;
    @PostMapping
    public Result<String> saveComment(@RequestBody GoodComment goodComment){
        //TODO,需要联表查询
        goodCommentService.save(goodComment);
        return Result.success(null,"评论发表成功");
    }
    @GetMapping("/{goodId}/{page}/{pageSize}")
    public Result<Page<GoodComment>> page(@PathVariable Long goodId, @PathVariable int page,
                                          @PathVariable int pageSize,Long fatherId){//fatherId为二级id,可为空
        Page<GoodComment> commentPage = new Page<>(page, pageSize);
        commentPage=goodCommentService.lambdaQuery()
                .eq(GoodComment::getGoodId,goodId)
                .eq(fatherId!=null,GoodComment::getFatherId,fatherId)
                .page(commentPage);
        return Result.success(commentPage,"获取评论列表成功");
    }
}
