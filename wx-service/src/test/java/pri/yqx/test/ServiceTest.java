package pri.yqx.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import pri.yqx.entity.Dormitory;
import pri.yqx.entity.Good;
import pri.yqx.json.PicUrl;
import pri.yqx.service.*;

import javax.annotation.Resource;
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
//        goodService.lambdaUpdate().set(Good::getStatus,0).update();
        List<Good> list = goodService.lambdaQuery() .select(Good::getGoodId,Good::getPicUrls).list();

        list.forEach(item->{
            List<PicUrl> picUrls = item.getPicUrls();
            picUrls.forEach(i->{
                String url = i.getUrl();
                i.setUrl(url.replace("sge9cddv1.hn-bkt.clouddn.com/api/download","sge9cddv1.hn-bkt.clouddn.com"));
            });
            boolean b = goodService.updateById(item);

        });

    }
    @Test
    public void testSelect(){

        List<Dormitory> list = dormitoryService.lambdaQuery().groupBy(Dormitory::getSchool).select(Dormitory::getSchool).list();
    }
    @Resource
    private GoodCommentService goodCommentService;
    @Test
    public void testCommentService(){
        goodCommentService.validateCommentId(1836046195828654081L);
    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testRedis(){
//        stringRedisTemplate.opsForValue().multiGet()
//        Good good = redisUtil.get("商品id:124234", Good.class);
        Good good = new Good().setGoodId(3452432L).setGoodNum(11).setHtml("房间都十分建瓯市的");
        redisTemplate.opsForValue().set(1213L, good);
        Object id = redisTemplate.opsForValue().get(1213L);
    }

}
