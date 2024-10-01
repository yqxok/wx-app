package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.common.QiniuOss;
import pri.yqx.dto.GoodDto;
import pri.yqx.entity.*;
import pri.yqx.json.PicUrl;
import pri.yqx.mapper.GoodMapper;
import pri.yqx.service.*;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.GoodDetailVo;
import pri.yqx.vo.GoodVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Lazy
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private GoodCategoryService goodCategoryService;


    @Resource
    private GoodCommentService goodCommentService;

    @Resource
    private GoodMapper goodMapper;
    @Resource
    private QiniuOss qiniuOss;

    @Override
    public void validateGoodId(Long goodId) {
        Long count = this.lambdaQuery().eq(Good::getGoodId, goodId).count();
        if(count<1)
            throw new RuntimeException("该商品id不存在");
    }

    @Override
    public Long saveGood(GoodDto goodDto) {
        Long count = userService.lambdaQuery().eq(User::getUserId, goodDto.getUserId()).count();
        if(count<1)
            throw new RuntimeException("不存在该userId");
        List<Category> list = categoryService.lambdaQuery().in(Category::getCategoryName, goodDto.getCategories()).list();
        if(list.size()!=goodDto.getCategories().size())
            throw new RuntimeException("category有误");
        Good good = MyBeanUtils.copyProperties(goodDto, new Good());
        long id = IdWorker.getId();
        good.setGoodId(id);
        save(good);
        List<GoodCategory> goodCategories = list.stream().map(category ->
                new GoodCategory().setGoodId(good.getGoodId())
                        .setCategoryName(category.getCategoryName()).setLevel(category.getLevel())
        ).collect(Collectors.toList());
        goodCategoryService.saveBatch(goodCategories);
        return id;
    }

    @Override
    public Page<GoodVo> pageGoodVo(int page, int pageSize,String categoryName) {
        int index= (page-1)*pageSize;
        List<GoodVo> goodVos = goodMapper.selectGoodVo(index, pageSize,categoryName);
        Page<GoodVo> goodVoPage = new Page<>(page, pageSize);
        goodVoPage.setRecords(goodVos)
                .setTotal(goodVos.size());
        return goodVoPage;

    }

    @Override
    public List<GoodVo> listGoodVoById(Long userId,Short status) {
        return goodMapper.getGoodVoList(userId,status);
    }

    @Override
    public void deleteGoodById(Long goodId,String prefix) {
//        goodCommentService.lambdaQuery().eq(GoodComment::getGoodId,goodId).
        Good good = this.getById(goodId);
        goodCommentService.remove(new LambdaQueryWrapper<GoodComment>().eq(GoodComment::getGoodId,goodId));
        goodCategoryService.remove(new LambdaQueryWrapper<GoodCategory>().eq(GoodCategory::getGoodId,goodId));
        this.removeById(goodId);
//        good.getPicUrls().forEach(item->{
////            System.out.println(item);
//            qiniuOss.deleteLocalByUrl(prefix, item.getUrl());
//        });
//        int i=1/0;
        //删除本地图片文件

    }

    @Override
    public GoodDetailVo getGoodDetailVo(Long goodId) {
        this.validateGoodId(goodId);
        return goodMapper.getGoodDetailVo(goodId);
    }

    @Override
    public GoodDetailVo updateGood(GoodDto goodDto) {
        Good good = MyBeanUtils.copyProperties(goodDto, new Good());

        goodCategoryService.remove(new LambdaQueryWrapper<GoodCategory>().eq(GoodCategory::getGoodId,goodDto.getGoodId()));
        List<Category> list = categoryService.lambdaQuery().in(Category::getCategoryName, goodDto.getCategories()).list();
        if(list.size()!=goodDto.getCategories().size())
            throw new RuntimeException("category有误");
        List<GoodCategory> goodCategories = list.stream().map(category ->
                new GoodCategory().setGoodId(good.getGoodId())
                        .setCategoryName(category.getCategoryName()).setLevel(category.getLevel())
        ).collect(Collectors.toList());
        goodCategoryService.saveBatch(goodCategories);
        this.updateById(good);
        return goodMapper.getGoodDetailVo(good.getGoodId());
    }
}
