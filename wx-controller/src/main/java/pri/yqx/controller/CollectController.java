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
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.CollectVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collect")
@Slf4j
public class CollectController {
    @Resource
    private CollectService collectService;
    @PostMapping
    public Result<String> post(@RequestBody @Validated(Insert.class) CollectDto collectDto){
        collectService.saveCollect(collectDto);
        return Result.success(null,"收藏成功");
    }
    @GetMapping("/{userId}/{page}/{pageSize}")
    public Result<Page<CollectVo>> page(@PathVariable Long userId, @PathVariable int page, @PathVariable int pageSize){
        log.info("userId={},page={},pageSize={}",userId,page,pageSize);
        Page<Collect> collectPage = new Page<>(page, pageSize);
        collectPage= collectService.lambdaQuery().eq(Collect::getUserId,userId).page(collectPage);

        Page<CollectVo> collectVoPage = MyBeanUtils.copyPage(collectPage, CollectVo::new);
        return Result.success(collectVoPage,"收藏查询成功");
    }
}