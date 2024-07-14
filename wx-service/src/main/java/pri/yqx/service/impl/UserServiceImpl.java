package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.entity.User;
import pri.yqx.mapper.UserMapper;
import pri.yqx.service.UserService;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User login(String openId,String sessionKey) {
        User one = lambdaQuery().eq(User::getOpenId, openId).one();

        if(one!=null){
            one.setSessionKey(null)
                    .setOpenId(null)
                    .setPassword(null);
            return one;
        }
        //首次登录注册,需要设置昵称
        User user = new User().setOpenId(openId).setSessionKey(sessionKey).setUserName("莞工er"+ UUID.randomUUID().toString());
        save(user);
        User one1 = lambdaQuery().eq(User::getOpenId, openId).one();
        one1.setOpenId(null)
                .setSessionKey(null);
        return null;
    }
}
