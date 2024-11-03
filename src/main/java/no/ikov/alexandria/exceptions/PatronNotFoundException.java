package no.ikov.alexandria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Patron not found")
public class PatronNotFoundException extends RuntimeException {

    public PatronNotFoundException(int id) {
        super("Patron with id " + id + " not found");
    }

}
