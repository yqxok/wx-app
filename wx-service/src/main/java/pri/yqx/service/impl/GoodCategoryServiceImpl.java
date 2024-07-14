package pri.yqx.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import pri.yqx.entity.GoodCategory;

import pri.yqx.mapper.GoodCategoryMapper;
import pri.yqx.service.GoodCategoryService;


@Service
public class GoodCategoryServiceImpl extends ServiceImpl<GoodCategoryMapper,GoodCategory> implements GoodCategoryService {

}
