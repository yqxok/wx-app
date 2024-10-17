package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.AddressDto;
import pri.yqx.entity.Address;
import pri.yqx.entity.Dormitory;
import pri.yqx.exceptions.BusinessException;
import pri.yqx.mapper.AddressMapper;
import pri.yqx.service.AddressService;
import pri.yqx.service.DormitoryService;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.AddressVo;

import javax.annotation.Resource;

import java.util.List;

@Service
@Transactional
@Slf4j
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Resource
    private DormitoryService dormitoryService;
    @Resource
    private UserService userService;

    @Override
    public void validateAddressId(Long addressId) {

        Long count = this.lambdaQuery().eq(Address::getAddressId, addressId).count();
        if(count<1)
            throw new BusinessException("该addressId不存在");
    }

    @Override
    public List<AddressVo> getAddressVoList(Long userId) {
        return addressMapper.getAddressVoList(userId);

    }

    @Override
    public void saveAddress(AddressDto addressDto) {
        userService.validateUserId(addressDto.getUserId());
        Dormitory one = dormitoryService.lambdaQuery().eq(Dormitory::getSchool, addressDto.getSchool())
                .eq(Dormitory::getZone, addressDto.getZone()).eq(Dormitory::getDormiName, addressDto.getDormiName()).one();
        if(one==null)
            throw new BusinessException("addressDto的school,zone,dormiName字段有误");
        Long dormitoryId = one.getDormitoryId();
        Address address = MyBeanUtils.copyProperties(addressDto, new Address());
        address.setDormiId(one.getDormitoryId());
        this.save(address);
    }

    @Override
    public void updateAddress(AddressDto addressDto) {
        userService.validateUserId(addressDto.getUserId());
        this.validateAddressId(addressDto.getAddressId());
        Dormitory one = dormitoryService.lambdaQuery().eq(Dormitory::getSchool, addressDto.getSchool())
                .eq(Dormitory::getZone, addressDto.getZone()).eq(Dormitory::getDormiName, addressDto.getDormiName()).one();
        if(one==null)
            throw new BusinessException("addressDto的school,zone,dormiName字段有误");
        Address address = MyBeanUtils.copyProperties(addressDto, new Address());
        address.setDormiId(one.getDormitoryId());

        this.updateById(address);
    }

    @Override
    public void updateDefaultAddress(AddressDto addressDto) {
        this.validateAddressId(addressDto.getAddressId());
        this.userService.validateUserId(addressDto.getUserId());
        Address address = new Address().setIsDefault(false);
        this.lambdaUpdate().eq(Address::getUserId,addressDto.getUserId())
                .set(Address::getIsDefault,false).update();
        this.lambdaUpdate().eq(Address::getAddressId,addressDto.getAddressId())
                .set(Address::getIsDefault,true).update();
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