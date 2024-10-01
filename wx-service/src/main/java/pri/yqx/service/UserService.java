package pri.yqx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pri.yqx.dto.LoginDto;
import pri.yqx.entity.User;
import pri.yqx.vo.UserVo;


public interface UserService extends IService<User>  {
    UserVo login(String openId, String sessionId);

    public UserVo loginWithPhone(LoginDto loginDto);

    public void signIn(LoginDto loginDto);
    public void validateUserId(Long userId);
}
