package pri.yqx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.yqx.common.Result;
import pri.yqx.entity.Dormitory;
import pri.yqx.service.DormitoryService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Resource
    private DormitoryService dormitoryService;
    @GetMapping
    public Result<List<String>> dormitoryVos(String zone, String school){
        dormitoryService.getDormitoryVos(zone,school);
       if(school==null&&zone==null) {
           List<Dormitory> list = dormitoryService.lambdaQuery().groupBy(Dormitory::getSchool).select(Dormitory::getSchool).list();
           List<String> collect = list.stream().map(Dormitory::getSchool).collect(Collectors.toList());

           return Result.success(collect, "学校查询成功");
       }else if(school!=null&&zone==null){
           List<Dormitory> list = dormitoryService.lambdaQuery().eq(Dormitory::getSchool, school).groupBy(Dormitory::getZone).select(Dormitory::getZone).list();
           List<String> collect = list.stream().map(Dormitory::getZone).collect(Collectors.toList());
           return Result.success(collect,"校区查询成功");
       }else if(school!=null&&zone!=null){
           List<Dormitory> list = dormitoryService.lambdaQuery().eq(Dormitory::getSchool, school)
                   .eq(Dormitory::getZone, zone).list();
           List<String> collect = list.stream().map(Dormitory::getDormiName).collect(Collectors.toList());
           return Result.success(collect,"宿舍楼查询成功");
       }else
           throw new RuntimeException("dormitory Mapping 查询参数有误");

    }

}
