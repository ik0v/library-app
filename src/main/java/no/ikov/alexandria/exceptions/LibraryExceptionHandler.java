package no.ikov.alexandria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class LibraryExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException() {
        return new ResponseEntity<>(ErrorResponse
                .builder()
                .errorMessage("Book not found")
                .statusCode(404)
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
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
