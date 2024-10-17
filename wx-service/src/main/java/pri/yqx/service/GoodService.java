package pri.yqx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.GoodDto;
import pri.yqx.entity.Good;
import pri.yqx.vo.GoodDetailVo;
import pri.yqx.vo.GoodVo;

import java.util.List;

public interface GoodService extends IService<Good> {
    public void validateGoodId(Long goodId);
    public Long saveGood(GoodDto goodDto);
    public Page<GoodVo> pageGoodVo(int page,int pageSize,String categoryName);
    public List<GoodVo> listGoodVoById(Long userId,Short status);
    public void deleteGoodById(Long goodId,String token);
    public GoodDetailVo getGoodDetailVo(Long goodId);
    public Long updateGood(GoodDto goodDto);

}
