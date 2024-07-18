package pri.yqx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.GoodDto;
import pri.yqx.entity.Good;
import pri.yqx.groups.Insert;
import pri.yqx.service.GoodService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.GoodDetailVo;
import pri.yqx.vo.GoodVo;

import javax.annotation.Resource;

@RestController
@RequestMapping("/good")
@Slf4j
public class GoodController {
    @Resource
    private GoodService goodService;
    @PostMapping
    public Result<String> postGood(@RequestBody @Validated(Insert.class) GoodDto goodDto){
        log.info("goodDto={}",goodDto);
        goodService.saveGood(goodDto);
        return Result.success(null,"商品保存成功");
    }

    @GetMapping("/{page}/{pageSize}")
    public Result<Page<GoodVo>> getList(@PathVariable("page")int page, @PathVariable("pageSize")int pageSize){

        log.info("page={},pageSize={}",page,pageSize);
        Page<Good> goodPage = new Page<>(page, pageSize);
        goodPage = goodService.page(goodPage);
        Page<GoodVo> goodVoPage = MyBeanUtils.copyPage(goodPage, GoodVo::new);

        return Result.success(goodVoPage,"查询成功");
    }
}
