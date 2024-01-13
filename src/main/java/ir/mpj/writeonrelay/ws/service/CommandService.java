package ir.mpj.writeonrelay.ws.service;


import ir.mpj.writeonrelay.shared.MyApiResponse;
import org.springframework.http.ResponseEntity;

public interface CommandService {

    ResponseEntity<MyApiResponse> onAllRegisters() throws InterruptedException;
    ResponseEntity<MyApiResponse> offAllRegisters() throws InterruptedException;
}
