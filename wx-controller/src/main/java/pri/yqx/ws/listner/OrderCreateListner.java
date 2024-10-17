package pri.yqx.ws.listner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pri.yqx.dto.OrderMsgDto;
import pri.yqx.event.EventListner;
import pri.yqx.event.EventPublisher;
import pri.yqx.service.OrderMsgService;
import pri.yqx.vo.OrderMsgVo;
import pri.yqx.ws.GenericWsDto;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
@Component
public class OrderCreateListner implements EventListner {
    @Resource
    private ConcurrentHashMap<Long, Session> sessionMap;
    @Resource
    private OrderMsgService orderMsgService;

    @PostConstruct
    public void init(){
        EventPublisher.on("/order",this);
    }
    @Override
    public void handle(Object data)  {
        try {
            OrderMsgDto orderMsgDto = JSON.parseObject(String.valueOf((JSONObject)data),OrderMsgDto.class);
            Long orderMsgId = orderMsgService.saveOrderMsg(orderMsgDto);
            Session session = sessionMap.get(orderMsgDto.getReceiverId());
            if(session==null) return;
            OrderMsgVo orderMsgVo = orderMsgService.getOrderMsgVo(orderMsgId);
            GenericWsDto genericWsDto = new GenericWsDto("/order",orderMsgVo);
            session.getBasicRemote().sendText(JSON.toJSONString(genericWsDto));
        } catch (IOException e) {
            log.warn("OrderCreateListner序列化错误");

        }
    }
}
