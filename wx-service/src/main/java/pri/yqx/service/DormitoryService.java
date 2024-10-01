package pri.yqx.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.Category;
import pri.yqx.entity.Dormitory;

import java.util.List;

public interface DormitoryService extends IService<Dormitory> {
    public List<String> getDormitoryVos(String zone, String school);
}
