package no.ikov.alexandria.application.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.ikov.alexandria.application.author.Author;
import no.ikov.alexandria.application.bookevent.BookEvent;
import no.ikov.alexandria.application.location.Location;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_gen")
    @SequenceGenerator(name = "book_gen", sequenceName = "book_seq", allocationSize = 1)
    @Column(name = "book_id", nullable = false)
    private long id;
    private String title;
    private String publisher;
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnoreProperties("books")
    private List<Author> authors = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties("books")
    private Location location;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<BookEvent> events = new ArrayList<>();

    public Book(String title, String publisher, Status status, List<Author> authors, Location location) {
        this.title = title;
        this.publisher = publisher;
        this.status = status;
        this.authors = new ArrayList<>(authors);
        this.location = location;
    }
}
