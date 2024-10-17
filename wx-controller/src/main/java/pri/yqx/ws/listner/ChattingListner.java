package pri.yqx.ws.listner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pri.yqx.dto.ChatContentDto;
import pri.yqx.event.EventListner;
import pri.yqx.event.EventPublisher;
import pri.yqx.service.ChatContentService;
import pri.yqx.vo.ChatContentVo;
import pri.yqx.ws.GenericWsDto;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ChattingListner implements EventListner {
    @Resource
    private ConcurrentHashMap<Long, Session> sessionMap;
    @Resource
    private ChatContentService chatContentService;
    @PostConstruct
    public void init(){
        EventPublisher.on("/chatting",this);
    }
    @Override
    public void handle(Object data) {
        try {
            ChatContentDto chatContentDto = JSON.parseObject(String.valueOf((JSONObject)data),ChatContentDto.class);
            Session receiveSession = sessionMap.get(chatContentDto.getReceiveUserId());
            Session sendSession=sessionMap.get(chatContentDto.getSendUserId());
            //保存消息并返回vo
            ChatContentVo chatContentVo = chatContentService.saveAndGetChatContentVo(chatContentDto);
//            String data1=JSON.toJSONString(chatContentVo);
            GenericWsDto genericWsDto = new GenericWsDto("/chatting",chatContentVo);
            String msg=JSON.toJSONString(genericWsDto);
            //向发送方和接收方转发消息
            if(sendSession!=null)
                sendSession.getBasicRemote().sendText(msg);
            if(receiveSession!=null)
                receiveSession.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            log.warn("ChattingListner序列化错误");
        }
    }
}
