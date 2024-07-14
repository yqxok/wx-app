package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pri.yqx.entity.Category;
import pri.yqx.entity.Dormitory;
import pri.yqx.mapper.CategoryMapper;
import pri.yqx.mapper.DormitoryMapper;
import pri.yqx.service.CategoryService;
import pri.yqx.service.DormitoryService;

@Service
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {
}
