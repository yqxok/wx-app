package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.entity.Collect;
import pri.yqx.groups.Insert;
import pri.yqx.service.CollectService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/collect")
@Slf4j
public class CollectController {
    @Resource
    private CollectService collectService;
    @PostMapping
    public Result<String> post(@RequestBody @Validated(Insert.class) Collect collect){
        collectService.saveCollect(collect);
        return Result.success(null,"收藏成功");
    }
    @GetMapping("/{userId}/{page}/{pageSize}")
    public Result<Page<Collect>> page(@PathVariable Long userId,@PathVariable int page,@PathVariable int pageSize){
        log.info("userId={},page={},pageSize={}",userId,page,pageSize);
        collectService.checkExistOfUser(userId);
        Page<Collect> collectPage = new Page<>(page, pageSize);
        collectPage= collectService.lambdaQuery().eq(Collect::getUserId,userId).page(collectPage);
        return Result.success(collectPage,"收藏查询成功");
    }
}