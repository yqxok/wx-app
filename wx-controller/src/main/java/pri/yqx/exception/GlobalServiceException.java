package pri.yqx.exception;

import lombok.Getter;
import pri.yqx.enums.GlobalStatusCode;

/**
 * @author cattleyuan
 * @date 2024/7/16
 */
@Getter
public class GlobalServiceException extends RuntimeException{

    private final String message;

    private final GlobalStatusCode code;

    public GlobalServiceException(String message, GlobalStatusCode code) {
        this.message = message;
        this.code = code;
    }

    public GlobalServiceException( GlobalStatusCode code) {
        this.message = code.getMessage();
        this.code = code;
    }
}
