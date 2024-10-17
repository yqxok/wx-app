package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.CollectDto;
import pri.yqx.entity.Collect;
import pri.yqx.groups.Insert;
import pri.yqx.service.CollectService;
import pri.yqx.vo.CollectVo;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/collect")
@Slf4j
public class CollectController {
    @Resource
    private CollectService collectService;

    @GetMapping("/{userId}/{page}/{pageSize}")
    public Result<Page<CollectVo>> page(@PathVariable Long userId, @PathVariable int page, @PathVariable int pageSize){
        log.info("userId={},page={},pageSize={}",userId,page,pageSize);
        Page<CollectVo> collectVoPage = collectService.getCollectVoPage(userId, page, pageSize);
        return Result.success(collectVoPage,"收藏查询成功");
    }
    @GetMapping("/{userId}/{goodId}")
    public Result<Boolean> getCollectNum(@PathVariable Long userId, @PathVariable Long goodId){
        Collect one = collectService.lambdaQuery().eq(Collect::getUserId, userId).eq(Collect::getGoodId, goodId)
                .one();
        Boolean res= one != null;

        return Result.success(res,"收藏获取成功");
    }

    @PostMapping
    public Result<Boolean> saveCollect(@Validated(Insert.class) @RequestBody CollectDto collectDto){
        Boolean res = collectService.saveCollect(collectDto);
        return Result.success(res,"收藏保存成功");
    }
    @DeleteMapping("/{userId}")
    public Result<Boolean> deleteCollect(@RequestBody List<Long> goodIds,@PathVariable Long userId){
        collectService.deleteCollect(goodIds, userId);
        return Result.success(true,"收藏取消成功");
    }
}