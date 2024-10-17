package pri.yqx.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import pri.yqx.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class TimeCountInterceptor implements HandlerInterceptor {
    private final ThreadLocal<Long> map1=new ThreadLocal<>();
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        map1.set(System.currentTimeMillis());
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        try {
            Long begin = map1.get();
            Thread thread = Thread.currentThread();
            Long end=System.currentTimeMillis();
            String method = request.getMethod();
            String requestURI = request.getRequestURI();
            log.info("请求{}-{}用时{}毫秒",requestURI,method,end-begin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            map1.remove();
        }
    }

}
