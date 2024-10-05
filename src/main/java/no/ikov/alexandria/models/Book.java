package no.ikov.alexandria.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookId;
    private String title;
    private Author author;
    private String publisher;
    private Status status;
    private Location shelfLocation;
}
