package ir.mpj.writeonrelay.ws.service.impl;

import com.fazecast.jSerialComm.SerialPort;
import ir.mpj.writeonrelay.config.exception.AppException;
import ir.mpj.writeonrelay.shared.CommandUtil;
import ir.mpj.writeonrelay.shared.ErrorMessage;
import ir.mpj.writeonrelay.ws.model.Commands;
import ir.mpj.writeonrelay.ws.service.CommandService;
import ir.mpj.writeonrelay.shared.MyApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CommandServiceImpl implements CommandService {

    @Value("${delay.time}")
    private int delayTime;
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final Commands commands;
    private  SerialPort chosenPort ;
    private final CommandUtil commandUtil;
    public CommandServiceImpl(Commands commands, CommandUtil commandUtil) {
        this.commands = commands;
        this.commandUtil = commandUtil;
    }

    private void setConfig(){
        chosenPort = SerialPort.getCommPorts()[0];
        chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING,0,0);
        chosenPort.setComPortParameters(9600,8,1,0);
        chosenPort.openPort();
        if (! chosenPort.isOpen()){
            throw new AppException(ErrorMessage.COULD_NOT_OPEN_PORT.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<MyApiResponse> onAllRegisters() throws InterruptedException {
        setConfig();
        for(String temp: commands.getOnRegCommands()){
            byte[] byteTemp = commands.toBytes(temp);
            int result = chosenPort.writeBytes(byteTemp,byteTemp.length);
            if (result != 8){
                logger.log(Level.OFF,"Problem in write 8 byte of command {0}",temp);
                throw new AppException(ErrorMessage.COULD_NOT_TRANSFER_COMMAND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Thread.sleep(delayTime);
        }
        chosenPort.closePort();
        return commandUtil.createResponse("On all Pout reg successfully",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MyApiResponse> offAllRegisters() throws InterruptedException {
        setConfig();
        for(String temp: commands.getOffRegCommands()){
            byte[] byteTemp = commands.toBytes(temp);
            int result = chosenPort.writeBytes(byteTemp,byteTemp.length);
            if (result != 8){
                logger.log(Level.OFF,"Problem in write 8 byte of command {0}",temp);
                throw new AppException(ErrorMessage.COULD_NOT_TRANSFER_COMMAND.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Thread.sleep(delayTime);
        }
        chosenPort.closePort();
        return commandUtil.createResponse("Off all Pout reg successfully",HttpStatus.OK);
    }

}
