package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pri.yqx.entity.Category;
import pri.yqx.entity.GoodComment;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.mapper.GoodCommentMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.GoodCommentService;

@Service
public class GoodCommentServiceImpl extends ServiceImpl<GoodCommentMapper, GoodComment> implements GoodCommentService {
    @Override
    public void saveComment(GoodComment goodComment) {

    }
}
