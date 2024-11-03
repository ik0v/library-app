package no.ikov.alexandria.application.bookevent;

import no.ikov.alexandria.application.book.Book;
import no.ikov.alexandria.application.book.BookRepo;
import no.ikov.alexandria.application.book.BookService;
import no.ikov.alexandria.application.book.Status;
import no.ikov.alexandria.application.patron.Patron;
import no.ikov.alexandria.application.patron.PatronRepo;
import no.ikov.alexandria.exceptions.BookNotFoundException;
import no.ikov.alexandria.exceptions.PatronNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookEventService {

    private final BookEventRepo bookEventRepo;
    private final BookRepo bookRepo;
    private final PatronRepo patronRepo;

    public BookEventService(BookEventRepo bookEventRepo, BookRepo bookRepo, PatronRepo patronRepo) {
        this.bookEventRepo = bookEventRepo;
        this.bookRepo = bookRepo;
        this.patronRepo = patronRepo;
    }

    public List<BookEvent> findAll() {
        return bookEventRepo.findAll();
    }

    public List<BookEvent> findBookEventsByBookId(long bookId) {
        return bookEventRepo.findBookEventByBookId(bookId);
    }

    public BookEvent findById(long id) {
        return bookEventRepo.findById(id).orElse(null);
    }

    public BookEvent save(BookEvent bookEvent) {
        return bookEventRepo.save(bookEvent);
    }

    public void deleteById(long id) {
        bookEventRepo.deleteById(id);
    }

    public BookEvent checkout(BookEventDto bookEventDto) {
        Book book;
        Patron patron;
        try {
            patron = patronRepo.findById(bookEventDto.patronId()).orElseThrow();
        } catch (Exception e) {
            throw new PatronNotFoundException((int) bookEventDto.patronId());
        } try {
            book = bookRepo.findById(bookEventDto.bookId()).orElseThrow();
        } catch (Exception e) {
            throw new BookNotFoundException((int) bookEventDto.bookId());
        }
        book.setStatus(Status.CHECKED_OUT);
        bookRepo.save(book);
        return bookEventRepo.save(new BookEvent(BookEventType.CHECKOUT, LocalDateTime.now(), book, patron));
    }

    public BookEvent checkIn(BookEventDto bookEventDto) {
        Book book;
        Patron patron;
        try {
            patron = patronRepo.findById(bookEventDto.patronId()).orElseThrow();
        } catch (Exception e) {
            throw new PatronNotFoundException((int) bookEventDto.patronId());
        } try {
            book = bookRepo.findById(bookEventDto.bookId()).orElseThrow();
        } catch (Exception e) {
            throw new BookNotFoundException((int) bookEventDto.bookId());
        }
        book.setStatus(Status.AVAILABLE);
        bookRepo.save(book);
        return bookEventRepo.save(new BookEvent(BookEventType.CHECK_IN, LocalDateTime.now(), book, patron));
    }

    public BookEvent findBookEventId(long bookEventId) {
        return bookEventRepo.findById(bookEventId).orElseThrow();
    }
}
