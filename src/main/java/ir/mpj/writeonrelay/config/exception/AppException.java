package ir.mpj.writeonrelay.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException{
    private final String exceptionMessage;
    private final HttpStatus httpStatus;

    public AppException(String exceptionMessage, HttpStatus httpStatus) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
        this.httpStatus = httpStatus;
    }
}
