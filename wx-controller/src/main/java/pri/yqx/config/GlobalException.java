package pri.yqx.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pri.yqx.common.Result;
import pri.yqx.exceptions.BusinessException;
import pri.yqx.exceptions.SystemException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SystemException.class)
    public Result<String> exception(SystemException systemException){

        return Result.error(500,"系统出现异常");
    }
    @ExceptionHandler(BusinessException.class)
    public Result<String> exception(BusinessException businessException){
        log.warn("业务异常",businessException);
        return Result.error(500,businessException.getMessage());
    }
    @ExceptionHandler(value = BindException.class)
    public Result<String> exceptionHandler(BindException e){
        String messages = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return Result.error(400,messages);
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result<String> validException(ConstraintViolationException e){

        String messages = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("；"));
        return Result.error(400,messages);
    }
}
