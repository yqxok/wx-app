package pri.yqx.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.dto.CollectDto;
import pri.yqx.entity.Category;
import pri.yqx.entity.Collect;
import pri.yqx.vo.CollectVo;


public interface CollectService extends IService<Collect> {
    void saveCollect(CollectDto collectDtO);
}
