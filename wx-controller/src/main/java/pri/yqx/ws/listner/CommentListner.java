package pri.yqx.ws.listner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pri.yqx.dto.CommentMsgDto;
import pri.yqx.event.EventListner;
import pri.yqx.event.EventPublisher;
import pri.yqx.service.CommentMsgService;
import pri.yqx.vo.CommentMsgVo;
import pri.yqx.ws.GenericWsDto;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Component
public class CommentListner implements EventListner {
    @Resource
    private ConcurrentHashMap<Long, Session> sessionMap;
    @Resource
    private CommentMsgService commentMsgService;

    @PostConstruct
    public void init(){
        EventPublisher.on("/comment",this);
    }
    @Override
    public void handle(Object data) {
        try {
            CommentMsgDto commentMsgDto = JSON.parseObject(String.valueOf((JSONObject)data), CommentMsgDto.class);
            CommentMsgVo commentMsgVo = this.commentMsgService.saveCommentMsg(commentMsgDto);
            Long receiverId = commentMsgDto.getReceiverId();
            Session session = sessionMap.get(receiverId);
            if(session==null) return;
            GenericWsDto genericWsDto = new GenericWsDto("/comment", commentMsgVo);
            session.getBasicRemote().sendText(JSON.toJSONString(genericWsDto));

        } catch (Exception e) {
            log.warn("CommentListner序列化错误");
        }

    }
}
