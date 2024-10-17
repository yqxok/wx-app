package pri.yqx.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.entity.*;
import pri.yqx.json.PicUrl;
import pri.yqx.mapper.*;
import pri.yqx.vo.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

@Slf4j
@SpringBootTest
@Transactional
//@Rollback(value = false)
public class TestJDBC {
    @Resource
    private DataSource dataSource;
    private List<PicUrl> picUrls;

    public static void main(String[] args) {

    }
    public static int largestRectangleArea(int[] heights) {
        int[] height=new int[heights.length+1];
        for(int i=0;i<heights.length;i++)
            height[i]=heights[i];
        height[height.length-1]=0;
        Stack<Integer> stack=new Stack<>();
        stack.push(0);
        int res=0;
        for(int i=1;i<height.length;i++){
            while(!stack.isEmpty()&&height[i]<height[stack.peek()]){
                Integer pop = stack.pop();
                int w=i-pop;
                if(!stack.isEmpty())
                    w=i-stack.peek()-1;
                res=Math.max(res,w*height[pop]);
            }
            stack.push(i);
        }
        return res;
    }

    public static boolean check(Map<Character,Integer> map1,Map<Character,Integer> map2){
        if(map2.size()<map1.size())
            return false;
        for(Map.Entry<Character,Integer> entry:map2.entrySet()){
            Integer value = entry.getValue();
            Character key = entry.getKey();
            Integer orDefault = map1.getOrDefault(key, 0);
            if(orDefault==0||value<orDefault)
                return false;
        }
        return true;
    }


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
    private AddressMapper addressMapper;
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
//        List<User> user = userMapper.selectList(null);
//        List<GoodOrder> goodOrders = goodOrderMapper.selectList(null);
//        List<GoodComment> goodComments = goodCommentMapper.selectList(null);
//        List<GoodCategory> categories = goodCategoryMapper.selectList(null);
//        List<Good> goods = goodMapper.selectList(null);
        List<Dormitory> dormitories = dormitoryMapper.selectList(null);
        List<Collect> collects = collectMapper.selectList(null);
        List<ChatContent> chatContents = chatContentMapper.selectList(null);
        List<Category> categories1 = categoryMapper.selectList(null);
//        log.warn("categories={}",goods);
    }
    @Test//插入测试
    public void myBatisPlusInsert(){
        Category category1 = new Category();

        category1.setCategoryName("Elect");
        category1.setPkId(null);  // Assuming it's a top-level category
        category1.setCreateUser(1001L);
        category1.setIsDeleted(1);
        int insert = categoryMapper.insert(category1);
    }
    @Test
    public void xmlTest(){
//        List<GoodVo> goodVos = goodMapper.selectGoodVo(0, 8);
//        GoodVo goodVo = goodMapper.getGoodVo(1825872692324225025L);
//        List<PicUrl> picUrls = goodVo.getPicUrls();

        for (int i = 0; i < picUrls.size(); i++) {
            PicUrl picUrl = picUrls.get(i);

        }
//        List<GoodVo> goodVoList = goodMapper.getGoodVoList(1810668892315697153L);
    }
    @Test
    public void testJson(){
//        Good good = goodMapper.selectById(1824427926772486146L);
    }
    @Test
    public void testAddressMapper(){
        List<AddressVo> addressVoList = addressMapper.getAddressVoList(1810668892315697153L);
    }
    @Test
    public void testChatContentMapper(){
//        List<ChatContentCountVo> chatContentCountVos = chatContentMapper.countUnRead(1810668892315697153L);
//        Integer integer = chatContentMapper.noReadCount(1L);
        List<ChatContentCountVo> chatContentCountVos = chatContentMapper.countUnRead(1838171619538964481L);
    }
    @Test
    public void testCollectMapper(){
//        CollectNumVo collectNum = collectMapper.getCollectNum(1810668892315697153L, 1824442010641321985L);
    }
    @Test
    public void commentMapperTest(){
//        List<GoodCommentVo> goodCommentVoList = goodCommentMapper.getGoodCommentVoList(1824689030287286273L);
    }
    @Test
    public void goodMapperTest(){
        List<GoodVo> goodVos = goodMapper.selectGoodVo(0, 8,"书籍" );
    }
    @Resource
    private OrderMsgMapper orderMsgMapper;
    @Test
    public void orderMapperTest(){
//        List<OrderMsgVo> orderMsgVoList = orderMsgMapper.getOrderMsgVoList(1810668892315697153L);
        OrderMsgCountVo orderMsgCountVo = orderMsgMapper.getOrderMsgCountVo(1810668892315697153L);
//        OrderMsgVo orderMsgVo = (OrderMsgVo) orderMsgVoList;

//        List<SimpleOrderVo> simpleOrderVos = goodOrderMapper.getSimpleOrderVos(1810668892315697153L, 0, 8, false);
//        OrderMsgVo orderMsg = goodOrderMapper.getOrderMsg(4534642334L);
    }
}
