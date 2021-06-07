package main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "It's the same account")
public class SameAccountsException extends RuntimeException{
    public SameAccountsException(String message) {
        super(message);
    }
}
