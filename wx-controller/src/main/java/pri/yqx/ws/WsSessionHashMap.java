package pri.yqx.ws;

import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WsSessionHashMap extends ConcurrentHashMap<String, Session> {
}
