package pri.yqx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pri.yqx.dto.LoginDto;
import pri.yqx.entity.User;
import pri.yqx.mapper.UserMapper;
import pri.yqx.service.UserService;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.UserVo;

import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public UserVo login(String openId, String sessionKey) {
        User one = lambdaQuery().eq(User::getOpenId, openId).one();

        if(one!=null){
            return MyBeanUtils.copyProperties(one, new UserVo());
        }
        //首次登录注册,需要设置昵称
        User user = new User().setOpenId(openId).setSessionKey(sessionKey).setUserName("莞工er"+new Random().nextInt(10000));
        save(user);
        User one1 = lambdaQuery().eq(User::getOpenId, openId).one();
        return MyBeanUtils.copyProperties(one1, new UserVo());
    }

    @Override
    public UserVo loginWithPhone(LoginDto loginDto) {
        User one = this.lambdaQuery().eq(User::getPhoneNumber, loginDto.getPhoneNumber())
                .eq(User::getPassword, loginDto.getPassword()).one();
        if(one==null)
            throw new RuntimeException("账号或密码错误");
        return MyBeanUtils.copyProperties(one,new UserVo());
    }

    @Override
    public void signIn(LoginDto loginDto) {
        User one = lambdaQuery().eq(User::getPhoneNumber, loginDto.getPhoneNumber()).one();
        if(one!=null)
            throw new RuntimeException("账号已存在");
        User user = new User().setUserName("莞工er" + new Random().nextInt(10000))
                .setProfilePicUrl("https://tse1-mm.cn.bing.net/th/id/OIP-C.QDl_Z7HdQWX_XbVYgBLJLQAAAA?rs=1&pid=ImgDetMain")
                .setPassword(loginDto.getPassword()).setPhoneNumber(loginDto.getPhoneNumber());
        this.save(user);
    }

    @Override
    public void validateUserId(Long userId) {
        Long count = this.lambdaQuery().eq(User::getUserId, userId).count();
        if(count<1)
            throw new RuntimeException("该userId无效");
    }
}
