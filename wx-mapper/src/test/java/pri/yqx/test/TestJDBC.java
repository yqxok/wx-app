package pri.yqx.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pri.yqx.entity.User;
import pri.yqx.mapper.UserMapper;

import javax.annotation.Resource;
import javax.sql.DataSource;
@Slf4j
@SpringBootTest
public class TestJDBC {
    @Resource
    private DataSource dataSource;

    @Test
    public void testJDBC(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int i= jdbcTemplate.queryForObject("select count(*) from wx_collect",Integer.class);
        System.out.println(i);
    }
    @Resource
    private UserMapper userMapper;
    @Test
    public void testMybatis(){
        User user = userMapper.selectOne(null);
        log.info("user={}",user);
    }
}
