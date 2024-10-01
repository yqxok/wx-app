package pri.yqx.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.dto.GoodCommentDto;
import pri.yqx.entity.Category;
import pri.yqx.entity.GoodComment;
import pri.yqx.vo.GoodCommentVo;

import java.util.List;


public interface GoodCommentService extends IService<GoodComment> {

   List<GoodCommentVo> getCommentVoList(Long goodId,Long userId);
   public void validateCommentId(Long commentId);

   public GoodCommentVo saveGoodComment(GoodCommentDto goodCommentDto);
}
