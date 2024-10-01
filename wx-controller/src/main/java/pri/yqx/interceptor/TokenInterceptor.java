package pri.yqx.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import pri.yqx.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        try {
            JwtUtil.verify(token);//验证失败则报错
        }catch (RuntimeException e){
            throw new RuntimeException("token校验失败");
        }
        return true;
    }
}
