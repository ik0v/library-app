package no.ikov.alexandria.exceptions;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        String errorMessage,
        int statusCode,
        LocalDateTime timestamp
) {
}
