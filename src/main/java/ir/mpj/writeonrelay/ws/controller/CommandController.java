package ir.mpj.writeonrelay.ws.controller;

import ir.mpj.writeonrelay.shared.MyApiResponse;
import ir.mpj.writeonrelay.ws.service.CommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CommandController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final CommandService commandService;

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping("/onAllRegs")
    public ResponseEntity<MyApiResponse> onAllRegisters() throws InterruptedException {
        ResponseEntity<MyApiResponse> result = commandService.onAllRegisters();
        logger.log(Level.INFO,"All 8 Pout register on");
        return result;
    }

    @GetMapping("/offAllRegs")
    public ResponseEntity<MyApiResponse> offAllRegisters() throws InterruptedException {
        ResponseEntity<MyApiResponse> result = commandService.offAllRegisters();
        logger.log(Level.INFO,"All 8 Pout register off");
        return result;
    }
}
