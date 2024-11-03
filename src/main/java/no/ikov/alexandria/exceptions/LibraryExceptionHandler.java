package no.ikov.alexandria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class LibraryExceptionHandler {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ErrorResponse> handleBookException(Exception ex) {
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .errorMessage(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PatronException.class)
    public ResponseEntity<ErrorResponse> handlePatronException(Exception ex) {
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .errorMessage(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLocationNotFoundException() {
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .errorMessage("Location not found")
                .statusCode(404)
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }
}
