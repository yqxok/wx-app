package pri.yqx.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pri.yqx.mapper.UserMapper;

import javax.annotation.Resource;

@SpringBootTest
public class ControllerTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void test1(){
        System.out.println(userMapper);
    }

}
