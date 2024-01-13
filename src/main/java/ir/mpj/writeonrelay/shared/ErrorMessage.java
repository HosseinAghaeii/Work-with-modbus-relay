package ir.mpj.writeonrelay.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    COULD_NOT_OPEN_PORT("Could not open port of relay"),
    COULD_NOT_TRANSFER_COMMAND("There is a problem in transfer command(on or off)");

    private final String message;
}
