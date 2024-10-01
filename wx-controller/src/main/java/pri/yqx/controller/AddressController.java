package pri.yqx.controller;

import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.AddressDto;
import pri.yqx.entity.Address;
import pri.yqx.service.AddressService;
import pri.yqx.vo.AddressVo;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/address")
@RestController
public class AddressController {
    @Resource
    private AddressService addressService;
    @GetMapping("/{userId}")
    public Result<List<AddressVo>> addressList(@PathVariable Long userId){
        //TODO,校验userId有效性
        List<AddressVo> addressVoList = addressService.getAddressVoList(userId);
        return Result.success(addressVoList,"地址列表查询成功");
    }
    @GetMapping("/{userId}/default")
    public Result<AddressVo> getDefaultAddress(@PathVariable Long userId){
        AddressVo defaultAddress = addressService.getDefaultAddress(userId);
        return Result.success(defaultAddress,"默认地址查询成功");
//        Address one = addressService.lambdaQuery().eq(Address::getUserId, userId).eq(Address::getIsDefault, true).one();

    }
    @PostMapping("/{userId}")
    public Result<String> saveAddress(@RequestBody AddressDto addressDto,@PathVariable Long userId){
        addressService.saveAddress(addressDto,userId);
        return Result.success(null,"地址保存成功");
    }
    @PutMapping("/{addressId}")
    public Result<String> updateAddress(@RequestBody AddressDto addressDto,@PathVariable Long addressId){
        addressService.updateAddress(addressDto,addressId);
        return Result.success(null,"地址修改成功");
    }
    @PutMapping("/{userId}/{addressId}")
    public Result<String> updateDefaultAddress(@PathVariable Long userId,@PathVariable Long addressId){
        addressService.updateDefaultAddress(userId,addressId);

        return Result.success(null,"默认地址更新成功");
    }
    @DeleteMapping("/{addressId}")
    public Result<String> deleteAddress(@PathVariable Long addressId){
        addressService.removeById(addressId);
        return Result.success(null,"地址删除成功");
    }

}
