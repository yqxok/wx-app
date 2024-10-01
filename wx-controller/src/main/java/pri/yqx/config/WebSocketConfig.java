package pri.yqx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Bean//userId,seseion对象
    public ConcurrentHashMap<Long, Session> sessionMap(){
        return new ConcurrentHashMap<>();
    }
}
