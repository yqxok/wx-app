package pri.yqx.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.CategoryDto;
import pri.yqx.entity.Category;
import pri.yqx.entity.GoodCategory;
import pri.yqx.groups.Insert;
import pri.yqx.service.CategoryService;
import pri.yqx.service.GoodCategoryService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@Validated
@Slf4j
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @Resource
    private GoodCategoryService goodCategoryService;
    @PostMapping
    public Result<String> post(@RequestBody @Validated(Insert.class) CategoryDto categoryDto){
        categoryService.saveCategory(categoryDto);
        return Result.success(null,"添加分类成功");
    }
    @DeleteMapping("/{categoryName}")
    public Result<String> deleteById(@PathVariable String categoryName){
        categoryService.deleteById(categoryName);
        return Result.success(null,"删除成功");
    }
    @GetMapping
    public Result<List<String>> getById(String pkId){
        log.info("pkId={}",pkId);
        List<Category> list = categoryService.lambdaQuery()
                .eq(pkId!=null, Category::getPkId, pkId)
                .isNull(pkId==null,Category::getPkId)
                .list();
        List<String> collect = list.stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
        return Result.success(collect,"查询成功");
    }
    @GetMapping("/no/{goodId}")
    public Result<List<String>> getCategoryByGoodId(@PathVariable Long goodId){
        List<GoodCategory> list = goodCategoryService.lambdaQuery()
                .eq(GoodCategory::getGoodId, goodId)
                .orderByAsc(GoodCategory::getLevel)
                .list();
        List<String> collect = list.stream().map(GoodCategory::getCategoryName).collect(Collectors.toList());
        return Result.success(collect,"分类查询成功");
    }
}
