package pri.yqx.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.entity.Category;
import pri.yqx.groups.Insert;
import pri.yqx.service.CategoryService;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public Result<String> post(@RequestBody @Validated(Insert.class) Category category){
        categoryService.saveCategory(category);
        return Result.success(null,"添加分类成功");
    }
    @DeleteMapping("/{id}")
    public Result<String > deleteById(@Min(1L) @PathVariable Long id){
        categoryService.deleteById(id);
        return Result.success(null,"删除成功");
    }
}
