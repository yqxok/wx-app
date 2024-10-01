package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.GoodCommentDto;
import pri.yqx.entity.Category;
import pri.yqx.entity.GoodComment;
import pri.yqx.event.EventPublisher;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.mapper.GoodCommentMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.GoodCommentService;
import pri.yqx.service.GoodService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.GoodCommentVo;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Lazy
@Transactional
public class GoodCommentServiceImpl extends ServiceImpl<GoodCommentMapper, GoodComment> implements GoodCommentService {
    @Resource
    private GoodCommentMapper goodCommentMapper;

    @Resource
    private GoodService goodService;
    @Resource
    private UserService userService;

    @Override
    public List<GoodCommentVo> getCommentVoList(Long goodId,Long userId) {
        List<GoodCommentVo> commentVoList = goodCommentMapper.getGoodCommentVoList(goodId,userId);
        Map<Long, GoodCommentVo> noFatherMap = commentVoList.stream().filter(item -> item.getFatherId() == null)
                .map(item-> {
                    item.setSonComments(new ArrayList<>());
                    item.setSonCommentNum(0);
                    return item;
                })
                .collect(Collectors.toMap(GoodCommentVo::getCommentId, i -> i));
        commentVoList.forEach(item->{
            Long fatherId = item.getFatherId();
            GoodCommentVo goodCommentVo = noFatherMap.get(fatherId);
            if(goodCommentVo!=null){
                goodCommentVo.getSonComments().add(item);
                goodCommentVo.setSonCommentNum(goodCommentVo.getSonCommentNum()+1);
            }
        });
        ArrayList<GoodCommentVo> goodCommentVos = new ArrayList<>(noFatherMap.values());
        goodCommentVos.sort((i,j)->
            i.getTrueCreateTime().isBefore(j.getTrueCreateTime())?1:-1
        );
        return goodCommentVos;
    }

    @Override
    public void validateCommentId(Long commentId) {
        Long count = this.lambdaQuery().eq(GoodComment::getCommentId, commentId).count();
        if(count<1)
            throw new RuntimeException("该commentId无效");
    }

    @Override
    public GoodCommentVo saveGoodComment(GoodCommentDto goodCommentDto) {
        goodService.validateGoodId(goodCommentDto.getGoodId());
        userService.validateUserId(goodCommentDto.getUserId());
        GoodComment goodComment = MyBeanUtils.copyProperties(goodCommentDto, new GoodComment());
        long commentId = IdWorker.getId();
        goodComment.setCommentId(commentId);
        this.save(goodComment);

        return goodCommentMapper.getGoodCommentVo(commentId);
    }
}
