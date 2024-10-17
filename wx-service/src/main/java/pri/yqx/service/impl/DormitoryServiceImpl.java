package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.entity.Dormitory;
import pri.yqx.mapper.DormitoryMapper;
import pri.yqx.service.DormitoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {
    @Override
    public List<String> getDormitoryVos(String zone, String school) {
        if(school==null&&zone==null) {
            List<Dormitory> list = this.lambdaQuery().groupBy(Dormitory::getSchool)
                    .select(Dormitory::getSchool)
                    .list();

            return list.stream().map(Dormitory::getSchool).collect(Collectors.toList());
        }else if(school!=null&&zone==null){
            List<Dormitory> list = this.lambdaQuery().eq(Dormitory::getSchool, school).groupBy(Dormitory::getZone).select(Dormitory::getZone).list();
            return list.stream().map(Dormitory::getZone).collect(Collectors.toList());
        }else if(school!=null&&zone!=null){
            List<Dormitory> list = this.lambdaQuery().eq(Dormitory::getSchool, school)
                    .eq(Dormitory::getZone, zone).orderByAsc(Dormitory::getDormiName).list();
            return list.stream().map(Dormitory::getDormiName).collect(Collectors.toList());
        }else
            throw new RuntimeException("dormitory Mapping 查询参数有误");
    }
}
