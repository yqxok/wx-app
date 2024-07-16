package pri.yqx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pri.yqx.common.Result;
import pri.yqx.enums.GlobalStatusCode;
import pri.yqx.exception.GlobalServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalServiceException.class)
    public Result handleGlobalServiceException(GlobalServiceException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        GlobalStatusCode globalStatusCode = e.getCode();
        log.error("请求地址'{}', {}: '{}'", requestURI, globalStatusCode, message);
        return Result.error(globalStatusCode.getCode(),message);
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<String> exception(RuntimeException runtimeException){

        log.warn("全局异常捕获:{}",runtimeException.toString());
        return Result.error(500,runtimeException.getMessage());
    }
    @ExceptionHandler(value = BindException.class)
    public Result<String> exceptionHandler(BindException e){
        log.warn(e.toString());
        String messages = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return Result.error(400,messages);
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<String> validException(ConstraintViolationException e){

        log.warn(e.toString());
        String messages = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("；"));
        return Result.error(400,messages);
    }
}
