package pe.edu.idat.loan_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaximoLibrosException extends RuntimeException {
    public MaximoLibrosException(String message) {
        super(message);
    }
}
