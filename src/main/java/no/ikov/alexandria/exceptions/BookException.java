package no.ikov.alexandria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Book exception")
public class BookException extends RuntimeException {

    public BookException(String message) {
        super(message);
    }

}