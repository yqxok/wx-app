package pri.yqx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pri.yqx.entity.Address;
import pri.yqx.vo.AddressVo;

import java.util.List;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    List<AddressVo> getAddressVoList(Long userId);
}
