package pri.yqx.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;

public class HttpWebSocketConfig extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        List<String> token = request.getHeaders().get("token");
        if(token.size()!=0){
            String s = token.get(0);
            sec.getUserProperties().put("token",s);
        }else
            throw new RuntimeException("token为空");

    }
}
