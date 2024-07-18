package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.GoodCommentDto;
import pri.yqx.entity.GoodComment;
import pri.yqx.service.GoodCommentService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.GoodCommentVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/goodComment")
public class GoodCommentController {
    @Resource
    private GoodCommentService goodCommentService;
    @PostMapping
    public Result<String> saveComment(@RequestBody GoodCommentDto goodCommentDto){
        //TODO,需要联表查询
        GoodComment goodComment = MyBeanUtils.copyProperties(goodCommentDto, new GoodComment());
        goodCommentService.save(goodComment);
        return Result.success(null,"评论发表成功");
    }
    @GetMapping("/{goodId}/{page}/{pageSize}")
    public Result<Page<GoodCommentVo>> page(@PathVariable Long goodId, @PathVariable int page,
                                            @PathVariable int pageSize, Long fatherId){//fatherId为二级id,可为空
        Page<GoodComment> commentPage = new Page<>(page, pageSize);
        commentPage=goodCommentService.lambdaQuery()
                .eq(GoodComment::getGoodId,goodId)
                .eq(fatherId!=null,GoodComment::getFatherId,fatherId)
                .page(commentPage);
        Page<GoodCommentVo> goodCommentVoPage = MyBeanUtils.copyPage(commentPage, GoodCommentVo::new);
        return Result.success(goodCommentVoPage,"获取评论列表成功");
    }
}
