package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import pri.yqx.entity.Address;
import pri.yqx.mapper.AddressMapper;
import pri.yqx.service.AddressService;

import javax.jnlp.ServiceManager;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
}
