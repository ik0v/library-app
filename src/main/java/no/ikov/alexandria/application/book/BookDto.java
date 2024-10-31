package no.ikov.alexandria.application.book;

import java.util.List;

public record BookDto(
        String title,
        String publisher,
        long locationId,
        List<Long> authors
) {}
