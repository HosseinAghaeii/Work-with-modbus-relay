package ir.mpj.writeonrelay.shared;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MyApiResponse {
    private boolean action;
    private String message;
    private Date date;
    private Object result;
}
