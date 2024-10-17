package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.GoodCommentDto;
import pri.yqx.entity.GoodComment;
import pri.yqx.vo.GoodCommentVo;

import java.util.List;


public interface GoodCommentService extends IService<GoodComment> {

   List<GoodCommentVo> getCommentVoList(Long goodId,Long userId);
   public void validateCommentId(Long commentId);

   public GoodCommentVo saveGoodComment(GoodCommentDto goodCommentDto);
}
