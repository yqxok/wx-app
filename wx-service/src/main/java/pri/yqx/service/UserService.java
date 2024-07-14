package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import pri.yqx.entity.User;


public interface UserService extends IService<User>  {
    User login(String openId,String sessionId);
}
