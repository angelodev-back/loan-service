package pe.edu.idat.loan_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReservaYaExisteException extends RuntimeException {
    public ReservaYaExisteException(String message) {
        super(message);
    }
}
