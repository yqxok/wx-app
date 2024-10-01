package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.GoodJobDto;
import pri.yqx.entity.GoodComment;
import pri.yqx.entity.GoodJob;
import pri.yqx.mapper.GoodJobMapper;
import pri.yqx.service.GoodCommentService;
import pri.yqx.service.GoodJobService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;

import javax.annotation.Resource;

@Service
@Transactional
public class GoodJobServiceImpl extends ServiceImpl<GoodJobMapper, GoodJob> implements GoodJobService {

    @Resource
    private UserService userService;
    @Resource
    private GoodCommentService goodCommentService;
    @Override
    public void saveGoodJob(GoodJobDto goodJobDto) {
        goodCommentService.validateCommentId(goodJobDto.getCommentId());
        userService.validateUserId(goodJobDto.getUserId());
        GoodJob goodJob = MyBeanUtils.copyProperties(goodJobDto, new GoodJob());
        this.save(goodJob);
        goodCommentService.lambdaUpdate().eq(GoodComment::getCommentId,goodJob.getCommentId())
                .setSql("good_job_num=good_job_num+1").update();
    }

    @Override
    public void deleteGoodJob(GoodJobDto goodJobDto) {
        goodCommentService.validateCommentId(goodJobDto.getCommentId());
        userService.validateUserId(goodJobDto.getUserId());
        LambdaQueryWrapper<GoodJob> wrapper = new LambdaQueryWrapper<GoodJob>().eq(GoodJob::getUserId, goodJobDto.getUserId())
                .eq(GoodJob::getCommentId, goodJobDto.getCommentId());
        this.remove(wrapper);
        goodCommentService.lambdaUpdate().eq(GoodComment::getCommentId,goodJobDto.getCommentId())
                .setSql("good_job_num=good_job_num-1").update();
    }
}