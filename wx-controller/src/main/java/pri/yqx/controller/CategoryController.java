package pri.yqx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.CategoryDto;
import pri.yqx.entity.Category;
import pri.yqx.groups.Insert;
import pri.yqx.service.CategoryService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.CategoryVo;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@Validated
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @PostMapping
    public Result<String> post(@RequestBody @Validated(Insert.class) CategoryDto categoryDto){
        categoryService.saveCategory(categoryDto);
        return Result.success(null,"添加分类成功");
    }
    @DeleteMapping("/{id}")
    public Result<String> deleteById(@Min(1L) @PathVariable Long id){
        categoryService.deleteById(id);
        return Result.success(null,"删除成功");
    }
    @GetMapping
    public Result<List<CategoryVo>> getById(Long pkId){
        log.info("pkId={}",pkId);

        List<Category> list = categoryService.lambdaQuery()
                .eq(pkId!=null, Category::getPkId, pkId)
                .isNull(pkId==null,Category::getPkId)
                .list();
        List<CategoryVo> collect = list.stream()
                .map(category -> MyBeanUtils.copyProperties(category, new CategoryVo()))
                .collect(Collectors.toList());
        return Result.success(collect,"查询成功");
    }

}
