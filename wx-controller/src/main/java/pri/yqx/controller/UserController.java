package pri.yqx.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pri.yqx.common.Result;
import pri.yqx.entity.User;
import pri.yqx.groups.Update;
import pri.yqx.properties.WeChatPoperties;
import pri.yqx.service.UserService;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private Validator validator;
    @Resource
    private WeChatPoperties weChatPoperties;
    @PostMapping("/login")
    public Result<User> login(@RequestBody Map<String,String> map) {
        log.info("map:{}",map);
        String url = String.format(weChatPoperties.getLoginUrl(), weChatPoperties.getAppId(), weChatPoperties.getAppSecret(), map.get("code"));
        //请求微信服务器
        String forObject = new RestTemplate().getForObject(url, String.class);
        //TODO,可能抛出异常
        Map map1 = JSON.parseObject(forObject, Map.class);
        String openid =(String) map1.get("openid");
        String session_key =(String) map1.get("session_key");

        User user=userService.login(openid,session_key);

        return Result.success(user,"注册成功" );
    }
    @PutMapping
    public Result<String> updateUser(@Validated(Update.class) @RequestBody User user){
        userService.updateById(user);
        return Result.success(null,"保存成功");
    }
    @GetMapping("/{userId}")
    public Result<User> getUser(@PathVariable @Min(1L) Long userId){
        User user = userService.getById(userId);
        if(user==null)
            //TODO 定制特定异常类
            throw new RuntimeException("账号不存在");
        user.setOpenId(null)
            .setSessionKey(null)
            .setPassword(null);
        return Result.success(user,"用户查询成功");
    }

}
