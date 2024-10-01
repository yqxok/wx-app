package pri.yqx.controller;

import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.GoodJobDto;
import pri.yqx.service.GoodJobService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goodJob")
public class GoodJobController {
    @Resource
    private GoodJobService goodJobService;
    @PostMapping
    public Result<String> saveGoodJob(@RequestBody GoodJobDto goodJobDto){
        goodJobService.saveGoodJob(goodJobDto);
        return Result.success(null,"点赞成功");
    }
    @DeleteMapping
    public Result<String> deleteGoodJob(@RequestBody GoodJobDto goodJobDto){
        goodJobService.deleteGoodJob(goodJobDto);
        return Result.success(null,"取消点赞成功");
    }
}