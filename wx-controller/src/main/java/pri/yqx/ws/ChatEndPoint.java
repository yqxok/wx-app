package pri.yqx.ws;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pri.yqx.config.HttpWebSocketConfig;
import pri.yqx.event.EventPublisher;
import pri.yqx.util.JwtUtil;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chatContent",configurator = HttpWebSocketConfig.class)
@Slf4j
public class ChatEndPoint implements ApplicationContextAware {
    private static  ApplicationContext applicationContext;

    //线程安全的hash表
    private static ConcurrentHashMap<Long, Session> sessionMap;

    private Long userId;
    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) throws IOException {
        try {
            Object token = endpointConfig.getUserProperties().get("token");
            this.userId=this.verifyToken((String) token);
            sessionMap.put(this.userId,session);
        } catch (Exception e) {
            log.warn("websocket连接失败",e);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session)  {
        GenericWsDto wsDto = JSON.parseObject(message, GenericWsDto.class);
        EventPublisher.emit(wsDto.getUri(),wsDto.getData());
    }

    @OnClose
    public void onClose(Session session) {
        log.info("Connection closed with session: "+userId);
        sessionMap.remove(this.userId);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ChatEndPoint.applicationContext=applicationContext;
        if(sessionMap==null)
            sessionMap=(ConcurrentHashMap<Long, Session>) applicationContext.getBean("sessionMap");
    }
    private Long verifyToken(String token) {//token验证成功返回userId
        if (token==null)
            throw new RuntimeException("websocket连接失败,token为空");
        DecodedJWT decodedJWT=JwtUtil.verify(token);
        String userIdStr = decodedJWT.getClaim("userId").asString();
        return Long.valueOf(userIdStr);
    }
}
