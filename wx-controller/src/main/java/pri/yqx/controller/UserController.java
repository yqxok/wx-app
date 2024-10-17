package pri.yqx.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pri.yqx.common.Result;
import pri.yqx.dto.LoginDto;
import pri.yqx.dto.UserDto;
import pri.yqx.entity.User;
import pri.yqx.properties.WeChatPoperties;
import pri.yqx.service.UserService;
import pri.yqx.util.JwtUtil;
import pri.yqx.util.MyBeanUtils;
import pri.yqx.vo.UserVo;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private WeChatPoperties weChatPoperties;
    @Resource
    private ConcurrentHashMap<Long, Session> sessionMap;
//    @PostMapping("/no/lgByC")
//    public Result<UserVo> loginWithCode(@RequestBody LoginDto loginDto) {
//        log.info("loginDto:{}",loginDto);
//        String url = String.format(weChatPoperties.getLoginUrl(), weChatPoperties.getAppId(), weChatPoperties.getAppSecret(), loginDto.getCode());
//        //请求微信服务器
//        String forObject = new RestTemplate().getForObject(url, String.class);
//        //TODO,可能抛出异常
//        Map map1 = JSON.parseObject(forObject, Map.class);
//        String openid =(String) map1.get("openid");
//        String session_key =(String) map1.get("session_key");
//
//        UserVo userVo=userService.login(openid,session_key);
//
//        String token =createJwttoken(userVo);
//        return Result.success(userVo,token);
//    }
    @PostMapping("/no/lgByP")
    public Result<String> loginWithPhone(@RequestBody LoginDto loginDto){
        UserVo userVo = userService.loginWithPhone(loginDto);
        HashMap<String, String> map = new HashMap<>();
        map.put("userId",Long.toString(userVo.getUserId()) );
        return Result.success(JwtUtil.getToken(map),"账号登录成功");
    }
    @PostMapping("/no/signIn")
    public Result<String> signIn(@RequestBody LoginDto loginDto){
        userService.signIn(loginDto);
        return Result.success(null,"账号保存成功");
    }


    @PutMapping
    public Result<String> updateUser(@Validated @RequestBody UserDto userDto){
        User user = MyBeanUtils.copyProperties(userDto, new User());
        userService.updateById(user);
        return Result.success(null,"保存成功");
    }
    @GetMapping("/no/{userId}")
    public Result<UserVo> getUser(@PathVariable Long userId){
        User byId = this.userService.getById(userId);
        UserVo userVo = MyBeanUtils.copyProperties(byId, new UserVo());

        return Result.success(userVo,"用户查询成功");
    }

    @GetMapping("/no/token")//使用token获取用户信息
    public Result<UserVo> getUserByToken(@RequestParam(required = true) String token){
        DecodedJWT verify = JwtUtil.verify(token);
        Long userId = JwtUtil.getUserId(token);
        User user = this.userService.getById(userId);
        UserVo userVo = MyBeanUtils.copyProperties(user, new UserVo());
        return Result.success(userVo,"用户查询成功");
    }
    @GetMapping("/no/udToken")
    public Result<String> updateToken(@RequestParam(required = true) String token){
        DecodedJWT verify = JwtUtil.verify(token);
        String userId = verify.getClaim("userId").asString();
        HashMap<String, String> map = new HashMap<>();
        map.put("userId",userId);
        return Result.success(JwtUtil.getToken(map),"token更新成功");
    }
    @GetMapping("/noRead/{userId}")
    public Result<Integer> getNoReadNum(@PathVariable Long userId){
        User one = userService.lambdaQuery().eq(User::getUserId, userId).select(User::getNoReadNum).one();
        return Result.success(one.getNoReadNum(),"未读消息数查询成功");
    }
}
