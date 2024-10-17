package pri.yqx.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import pri.yqx.exceptions.BusinessException;
import pri.yqx.exceptions.SystemException;

@Component
@Aspect
@Slf4j
public class ExceptionAop {
    @Pointcut("execution(* pri.yqx.service.impl.*.*(..))")
    public void pointCut(){}
    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception){
        //目标方法名
        if(exception instanceof BusinessException){
            throw (BusinessException)exception;
        }else{
            Signature signature = joinPoint.getSignature();
            String msg= signature.getDeclaringTypeName()+" "+signature.getName()+"出错了";
            log.error(msg,exception);
            throw new SystemException(exception);
        }
    }
//    @AfterReturning(value = "pointCut()",returning = "keys")
//    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object keys){
//        System.out.println(joinPoint);
//        log.info("{}出现异常",joinPoint.getTarget());
//    }


}
