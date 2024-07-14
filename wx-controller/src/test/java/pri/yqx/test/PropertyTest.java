package pri.yqx.test;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.Customizer;
import pri.yqx.properties.WeChatPoperties;

import javax.annotation.Resource;
import java.util.function.Function;

@SpringBootTest
public class PropertyTest {
    @Resource
    private WeChatPoperties weChatPoperties;
    @Test
    public void wechatTest(){
        System.out.println(weChatPoperties);
    }
    @Test
    public void func(){
        Customizer<Integer> customizer = (i) -> {

        };
    }
}
