package pri.yqx.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.entity.*;
import pri.yqx.mapper.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
//@Rollback(value = false)
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
    @Resource
    private GoodOrderMapper goodOrderMapper;
    @Resource
    private GoodCommentMapper goodCommentMapper;
    @Resource
    private GoodCategoryMapper goodCategoryMapper;
    @Resource
    private GoodMapper goodMapper;
    @Resource
    private DormitoryMapper dormitoryMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private ChatContentMapper chatContentMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Test//查询测试
    public void mybatisPlusSelect(){
        List<User> user = userMapper.selectList(null);
        List<GoodOrder> goodOrders = goodOrderMapper.selectList(null);
        List<GoodComment> goodComments = goodCommentMapper.selectList(null);
        List<Category> categories = goodCategoryMapper.selectList(null);
        List<Good> goods = goodMapper.selectList(null);
        List<Dormitory> dormitories = dormitoryMapper.selectList(null);
        List<Collect> collects = collectMapper.selectList(null);
        List<ChatContent> chatContents = chatContentMapper.selectList(null);
        List<Category> categories1 = categoryMapper.selectList(null);
        log.warn("categories={}",goods);
    }
    @Test//插入测试
    public void myBatisPlusInsert(){
        Category category1 = new Category();
        category1.setCategoryId(6L);
        category1.setCategoryName("Elect");
        category1.setPkId(null);  // Assuming it's a top-level category
        category1.setCreateUser(1001L);
        category1.setIsDeleted(false);
        int insert = categoryMapper.insert(category1);
    }
}
