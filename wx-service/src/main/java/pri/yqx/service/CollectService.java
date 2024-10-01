package pri.yqx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.CollectDto;
import pri.yqx.entity.Collect;
import pri.yqx.vo.CollectNumVo;
import pri.yqx.vo.CollectVo;

import java.util.List;


public interface CollectService extends IService<Collect> {
    public Boolean saveCollect(CollectDto collectDtO);
    Page<CollectVo> getCollectVoPage(Long userId,Integer page,Integer pageSize);
    CollectNumVo getCollectNum(Long userId, Long goodId);
    public void validateCollectId(Long collectId);
    public Boolean deleteCollect(List<Long> goodIds, Long userId);
}
