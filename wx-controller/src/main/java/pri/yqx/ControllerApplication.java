package pri.yqx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ControllerApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(ControllerApplication.class, args);
//        AuthenticationManager bean1 = context.getBean(AuthenticationManager.class);
    }
}
