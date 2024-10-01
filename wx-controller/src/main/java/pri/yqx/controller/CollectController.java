package pri.yqx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.CollectDto;
import pri.yqx.entity.Collect;
import pri.yqx.groups.Insert;
import pri.yqx.service.CollectService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.CollectNumVo;
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
//        CollectNumVo collectNum = collectService.getCollectNum(userId, goodId);
//        if(collectNum==null){
//            CollectNumVo collectNumVo = new CollectNumVo().setGoodId(null).setCollectNum(0).setIsCollected(false);
//            return Result.success(collectNumVo,"该用户没有此收藏");
//        }
        return Result.success(res,"收藏获取成功");
    }

    @PostMapping
    public Result<Boolean> saveCollect(@RequestBody CollectDto collectDto){
//        Collect collect = MyBeanUtils.copyProperties(collectDto, new Collect());
        Boolean res = collectService.saveCollect(collectDto);
        return Result.success(res,"收藏保存成功");
    }
    @DeleteMapping("/{userId}")
    public Result<Boolean> deleteCollect(@RequestBody List<Long> goodIds,@PathVariable Long userId){
        Boolean res = collectService.deleteCollect(goodIds, userId);
//        collectService.remove(new LambdaQueryWrapper<Collect>().eq(Collect::getUserId,userId).in(Collect::getGoodId,goodIds));
//        collectService.removeBatchByIds(collectIds);
        return Result.success(res,"收藏取消成功");
    }
}