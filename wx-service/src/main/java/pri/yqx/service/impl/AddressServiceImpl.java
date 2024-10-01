package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.AddressDto;
import pri.yqx.entity.Address;
import pri.yqx.entity.Dormitory;
import pri.yqx.mapper.AddressMapper;
import pri.yqx.service.AddressService;
import pri.yqx.service.DormitoryService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.AddressVo;

import javax.annotation.Resource;
import javax.jnlp.ServiceManager;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private DormitoryService dormitoryService;

    @Override
    public void validateAddressId(Long addressId) {
        Long count = this.lambdaQuery().eq(Address::getAddressId, addressId).count();
        if(count<1)
            throw new RuntimeException("该addressId不存在");
    }

    @Override
    public List<AddressVo> getAddressVoList(Long userId) {

        return addressMapper.getAddressVoList(userId);
    }

    @Override
    public void saveAddress(AddressDto addressDto,Long userId) {
        Dormitory one = dormitoryService.lambdaQuery().eq(Dormitory::getSchool, addressDto.getSchool())
                .eq(Dormitory::getZone, addressDto.getZone()).eq(Dormitory::getDormiName, addressDto.getDormiName()).one();
        if(one==null)
            throw new RuntimeException("addressDto的school,zone,dormiName字段有误");
        Long dormitoryId = one.getDormitoryId();
        Address address = MyBeanUtils.copyProperties(addressDto, new Address());
        address.setDormiId(one.getDormitoryId()).setUserId(userId);
        this.save(address);
        

    }

    @Override
    public void updateAddress(AddressDto addressDto,Long addressId) {
        Dormitory one = dormitoryService.lambdaQuery().eq(Dormitory::getSchool, addressDto.getSchool())
                .eq(Dormitory::getZone, addressDto.getZone()).eq(Dormitory::getDormiName, addressDto.getDormiName()).one();
        if(one==null)
            throw new RuntimeException("addressDto的school,zone,dormiName字段有误");
        Address address = MyBeanUtils.copyProperties(addressDto, new Address());
        address.setAddressId(addressId).setDormiId(one.getDormitoryId());

        this.updateById(address);
    }

    @Override
    public void updateDefaultAddress(Long userId, Long addressId) {
        Address address = new Address().setIsDefault(false);
        this.lambdaUpdate().eq(Address::getUserId,userId).update(address);
        address.setAddressId(addressId).setIsDefault(true);
        this.updateById(address);

    }

    @Override
    public AddressVo getDefaultAddress(Long userId) {
        Address one = this.lambdaQuery().eq(Address::getUserId, userId).eq(Address::getIsDefault, true).one();
        if(one==null)
            return null;
        Dormitory byId = dormitoryService.getById(one.getDormiId());
        AddressVo addressVo = MyBeanUtils.copyProperties(one, new AddressVo());
        return MyBeanUtils.copyProperties(byId, addressVo);

    }
}