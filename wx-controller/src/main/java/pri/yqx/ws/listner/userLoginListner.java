package pri.yqx.ws.listner;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pri.yqx.event.EventListner;
import pri.yqx.event.EventPublisher;
import pri.yqx.vo.UserLoginMsgVo;
import pri.yqx.ws.GenericWsDto;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class userLoginListner implements EventListner {
    @Resource
    private ConcurrentHashMap<Long, Session> sessionMap;
    @PostConstruct
    public void init(){
        EventPublisher.on("/userLogin",this);
    }
    @Override
    public void handle(Object data) {
        try {
            UserLoginMsgVo userLoginMsgVo =(UserLoginMsgVo)data;
            Session session = sessionMap.get(userLoginMsgVo.getReceiverId());
            if(session==null) return;
            GenericWsDto genericWsDto = new GenericWsDto("/userLogin", userLoginMsgVo);
            session.getBasicRemote().sendText(JSON.toJSONString(genericWsDto));
        } catch (IOException e) {
            log.warn("userLoginListner序列化错误");
        }

    }
}
