package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.QiniuOss;
import pri.yqx.common.Result;
import pri.yqx.dto.GoodDto;
import pri.yqx.entity.Good;
import pri.yqx.groups.Insert;
import pri.yqx.service.GoodService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.GoodDetailVo;
import pri.yqx.vo.GoodVo;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/good")
@Slf4j
public class GoodController {
    @Resource
    private GoodService goodService;

//    @Resource
//    private QiniuOss qiniuOss;
    @PostMapping
    public Result<Long> postGood(@RequestBody @Validated(Insert.class) GoodDto goodDto){
        log.info("goodDto={}",goodDto);

        Long goodId = goodService.saveGood(goodDto);
        return Result.success(goodId,"商品保存成功");
    }

    @GetMapping("/no/{page}/{pageSize}")
    public Result<Page<GoodVo>> getGoodPage(@PathVariable("page")int page,
                                            @PathVariable("pageSize")int pageSize,
                                            String categoryName){

        log.info("page={},pageSize={}",page,pageSize);
        Page<GoodVo> goodVoPage = goodService.pageGoodVo(page, pageSize,categoryName);

        return Result.success(goodVoPage,"查询成功");
    }
    @GetMapping("/no/{goodId}")
    public Result<GoodDetailVo> getGoodDetailVo(@PathVariable Long goodId){
        GoodDetailVo goodDetailVo = goodService.getGoodDetailVo(goodId);
        return Result.success(goodDetailVo,"商品信息查询成功");
    }
    @GetMapping("/no/list/{userId}")
    public Result<List<GoodVo>> getGoodListById(@PathVariable Long userId,
                                                @RequestParam(required = false) Short status){

        List<GoodVo> goodVos = goodService.listGoodVoById(userId,status);
        return Result.success(goodVos,"商品查询成功");
    }
    @DeleteMapping("/{goodId}/{prefix}")
    public Result<String> deleteGoodById(@PathVariable Long goodId,@PathVariable String prefix){

        goodService.deleteGoodById(goodId,prefix);
        return Result.success("删除成功");
    }
    @PutMapping
    public Result<GoodDetailVo> updateGood(@RequestBody(required = true) GoodDto goodDto){
        Good good = MyBeanUtils.copyProperties(goodDto, new Good());

        GoodDetailVo goodDetailVo = goodService.updateGood(goodDto);
        return Result.success(goodDetailVo,"更新成功");

    }
}
