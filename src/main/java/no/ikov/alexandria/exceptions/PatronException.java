package no.ikov.alexandria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Patron exception")
public class PatronException extends RuntimeException {

    public PatronException(String message) {
        super(message);
    }

}
