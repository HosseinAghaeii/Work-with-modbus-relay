package ir.mpj.writeonrelay.config.exception;

import ir.mpj.writeonrelay.shared.MyApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class AppExceptionHandler {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<Object> handleAppException(AppException appException) {
        logger.log(Level.OFF, "Error with this message : {0}", appException.getExceptionMessage());

        MyApiResponse response = MyApiResponse.builder()
                .action(false)
                .message(appException.getMessage())
                .date(new Date())
                .result(null)
                .build();
        return new ResponseEntity<>(response, appException.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception exception) {
        logger.log(Level.OFF, "Error just happen : {0}", exception.getMessage());

        MyApiResponse response = MyApiResponse.builder()
                .action(false)
                .message(exception.getMessage())
                .date(new Date())
                .result(null)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
