package main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Currency is not the same")
public class CurrencyNotTheSameException extends RuntimeException {
    public CurrencyNotTheSameException(String message) {
        super(message);
    }
}
