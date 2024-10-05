package no.ikov.alexandria.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookId;

    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotNull(message = "Author cannot be null")
    @Valid
    private Author author;
    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;
    private Status status;
    @NotNull(message = "Location cannot be null")
    @Valid
    private Location shelfLocation;
}
