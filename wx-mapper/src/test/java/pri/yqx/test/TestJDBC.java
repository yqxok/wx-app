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
import java.util.List;
import java.util.Stack;

import static org.apache.ibatis.ognl.DynamicSubscript.mid;

@Slf4j
@SpringBootTest
@Transactional
//@Rollback(value = false)
public class TestJDBC {
    @Resource
    private DataSource dataSource;
    private List<PicUrl> picUrls;

    public static void main(String[] args) {
        int[] nums={5,7,7,8,8,10};
        searchRange(nums,8);
    }
    public static int[] searchRange(int[] nums, int target) {
        if(nums.length<1)
            return new int[]{-1,-1};
        int l=0,r=nums.length-1,value=0, value1=nums.length-1;
        if(target<=nums[0])
            value=-1;
        else {
            while(l<=r){
                int mid=l+(r-l)/2;
                if(nums[mid]<target){
                    value=mid;
                    l=mid+1;
                }else if(nums[mid]>=target)
                    r=mid-1;
            }
        }

        l=0;
        r=nums.length;
        if(target>=nums[nums.length-1])
            value1=nums.length;
        else {
            while(l<=r){
                int mid=l+(r-l)/2;
                if(nums[mid]<=target){
                    l=mid+1;
                }else if(nums[mid]>target){
                    value1=mid;
                    r=mid-1;
                }
            }
        }
        int min=-1,max=-1;
        if(value+1< nums.length&&nums[value+1]==target){
            min=value+1;
            max=value+1;
        }
        if(value1-1>-1&&nums[value1-1]==target){
            min=min==-1?value1-1:min;
            max=value-1;
        }
        return new int[]{min,max};
    }
    public static int mySqrt(int x) {
        if(x==0)
            return 0;
        long l=1,r=x,value=1;
        while(l<=r){
            long mid=(l+r)/2;
            if(mid*mid<=x){
                value=mid;
                l=mid+1;
            }else if(mid*mid>x)
                r=mid-1;
        }
        return (int) value;
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
