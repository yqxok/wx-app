package pri.yqx.test;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import pri.yqx.entity.Dormitory;
import pri.yqx.entity.Good;
import pri.yqx.entity.GoodOrder;
import pri.yqx.entity.User;
import pri.yqx.service.DormitoryService;
import pri.yqx.service.GoodOrderService;
import pri.yqx.service.GoodService;
import pri.yqx.service.UserService;
import pri.yqx.vo.GoodVo;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
@Slf4j
public class ServiceTest {
    @Autowired
    private GoodService goodService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private UserService userService;
    @Test
    public void test1(){
//        Page<GoodVo> goodVoPage = goodService.pageGoodVo(1, 8);
//        log.info("{}", goodVoPage);
    }
    @Resource
    private GoodOrderService goodOrderService;
    @Test
    public void testUpdate(){
        this.goodOrderService.lambdaUpdate().eq(GoodOrder::getOrderId,1837822038640922625L)
                .set(GoodOrder::getStatus,1).update();

    }
    @Test
    public void testSelect(){
        List<Dormitory> list = dormitoryService.lambdaQuery().groupBy(Dormitory::getSchool).select(Dormitory::getSchool).list();
    }


}
