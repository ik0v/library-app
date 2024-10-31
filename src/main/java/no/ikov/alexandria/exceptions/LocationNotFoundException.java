package no.ikov.alexandria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "location not found")
public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(long id) {
        super("Location with id " + id + " not found");
    }
}
