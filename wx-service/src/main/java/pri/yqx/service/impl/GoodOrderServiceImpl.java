package pri.yqx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.common.ExpProcessor;
import pri.yqx.dto.GoodOrderDto;
import pri.yqx.dto.SimpleOrderDto;
import pri.yqx.entity.*;
import pri.yqx.mapper.GoodOrderMapper;
import pri.yqx.service.*;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.OrderMsgVo;
import pri.yqx.vo.SimpleOrderVo;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class GoodOrderServiceImpl extends ServiceImpl<GoodOrderMapper, GoodOrder> implements GoodOrderService {
    @Resource
    private UserService userService;
    @Resource
    private GoodService goodService;
    @Resource
    private GoodOrderMapper goodOrderMapper;

    @Override
    public Long saveGoodOrder(GoodOrderDto goodOrderDto) {
        userService.validateUserId(goodOrderDto.getDelivererId());
        userService.validateUserId(goodOrderDto.getReceiverId());
        Good one = goodService.lambdaQuery().eq(Good::getGoodId, goodOrderDto.getGoodId()).select(Good::getGoodNum).one();
        new ExpProcessor().add(one==null,"该商品不存在")
                .add(one.getGoodNum()<1,"该商品已下架")
                .add(one.getGoodNum()>one.getGoodNum(),"购买数量超过商品剩余数量").execute();

        GoodOrder goodOrder = MyBeanUtils.copyProperties(goodOrderDto, new GoodOrder());
        Long orderId=IdWorker.getId();
        goodOrder.setOrderId(orderId);
        save(goodOrder);
        this.goodService.lambdaUpdate().eq(Good::getGoodId,goodOrderDto.getGoodId())
                .set(Good::getGoodNum,one.getGoodNum()-goodOrderDto.getNumber())
                .set(one.getGoodNum()==goodOrderDto.getNumber(),Good::getStatus,1).update();
        return orderId;


    }

    @Override
    public Page<SimpleOrderVo> getSimpleOrderVoPage(SimpleOrderDto sDto) {
        int index=(sDto.getPage()-1)*sDto.getPageSize();
        sDto.setPage(index);
        List<SimpleOrderVo> simpleOrderVos = goodOrderMapper.getSimpleOrderVos(sDto);
        Page<SimpleOrderVo> simpleOrderVoPage = new Page<>(sDto.getPage(), sDto.getPageSize());
        simpleOrderVoPage.setRecords(simpleOrderVos);
        simpleOrderVoPage.setTotal(simpleOrderVos.size());
        simpleOrderVoPage.setCurrent(sDto.getPage());
        return simpleOrderVoPage;
    }

    @Override
    public void validateOrderId(Long orderId) {
        Long count = this.lambdaQuery().eq(GoodOrder::getOrderId, orderId).count();
        if(count<1)
            throw new RuntimeException("该orderId失效");
    }

    @Override
    public void updateStatus(GoodOrderDto goodOrderDto) {
        this.validateOrderId(goodOrderDto.getOrderId());
        if(goodOrderDto.getStatus()<0||goodOrderDto.getStatus()>2)
            throw new RuntimeException("修改状态错误,0<=status<=2");
        if(goodOrderDto.getStatus()==2){
            Good good = this.goodOrderMapper.getGoodByOrderId(goodOrderDto.getOrderId());
            this.goodService.lambdaUpdate().eq(Good::getGoodId,good.getGoodId())
                    .set(Good::getGoodNum,good.getGoodNum()+1)
                    .set(good.getStatus()==1,Good::getStatus,0).update();
        }
        this.lambdaUpdate().eq(GoodOrder::getOrderId,goodOrderDto.getOrderId())
                .set(GoodOrder::getStatus,goodOrderDto.getStatus()).update();

    }


}
