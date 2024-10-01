package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.GoodJobDto;
import pri.yqx.entity.GoodJob;

public interface GoodJobService extends IService<GoodJob> {
   public void saveGoodJob(GoodJobDto goodJobDto);

   public void deleteGoodJob(GoodJobDto goodJobDto);
}
