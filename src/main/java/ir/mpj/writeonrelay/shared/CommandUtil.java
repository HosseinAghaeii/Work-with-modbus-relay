package ir.mpj.writeonrelay.shared;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommandUtil {

    public ResponseEntity<MyApiResponse> createResponse(Object catResponse, HttpStatus httpStatus){
        if (catResponse == null || httpStatus == null){
            return null;
        }
        MyApiResponse apiResponse = new MyApiResponse();
        apiResponse.setAction(true);
        apiResponse.setMessage("");
        apiResponse.setDate(new Date());
        apiResponse.setResult(catResponse);
        return new ResponseEntity<>(apiResponse,httpStatus);
    }
}
