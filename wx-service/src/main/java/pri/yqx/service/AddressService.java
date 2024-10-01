package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.AddressDto;
import pri.yqx.entity.Address;
import pri.yqx.vo.AddressVo;

import java.util.List;

public interface AddressService extends IService<Address> {
    public void validateAddressId(Long addressId);
    public List<AddressVo> getAddressVoList(Long userId);

    void saveAddress(AddressDto addressDto,Long userId);

    void updateAddress(AddressDto addressDto,Long addressId);

    void updateDefaultAddress(Long userId, Long addressId);

    AddressVo getDefaultAddress(Long userId);
}
