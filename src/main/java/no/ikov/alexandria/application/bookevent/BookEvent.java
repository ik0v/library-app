package no.ikov.alexandria.application.bookevent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import no.ikov.alexandria.application.book.Book;
import no.ikov.alexandria.application.patron.Patron;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Getter @Service
@NoArgsConstructor
@Entity
@Table(name = "book_event")
public class BookEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_event_gen")
    @SequenceGenerator(name = "book_event_gen", sequenceName = "event_seq", allocationSize = 1)
    @Column(name = "event_id", nullable = false)
    private Long id;
    private BookEventType type;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    public BookEvent(BookEventType type, LocalDateTime date, Book book, Patron patron) {
        this.type = type;
        this.date = date;
        this.book = book;
        this.patron = patron;
    }
}
