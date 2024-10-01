package pri.yqx.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "wechat")
@Component
public class WeChatPoperties {
    private String appId;
    private String appSecret;
    private String loginUrl;
    private String phoneNumberUrl;
}
