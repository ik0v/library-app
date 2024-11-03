package no.ikov.alexandria.application.bookevent;

import no.ikov.alexandria.application.book.Book;
import no.ikov.alexandria.application.book.BookRepo;
import no.ikov.alexandria.application.book.Status;
import no.ikov.alexandria.application.patron.Patron;
import no.ikov.alexandria.application.patron.PatronRepo;
import no.ikov.alexandria.exceptions.BookException;
import no.ikov.alexandria.exceptions.PatronException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
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

    public BookEvent checkOut(BookEventDto bookEventDto) {
        Book book;
        Patron patron;
        try {
            patron = patronRepo.findById(bookEventDto.patronId()).orElseThrow();
        } catch (Exception e) {
            throw new PatronException("Patron with id " + bookEventDto.patronId() + " not found");
        } try {
            book = bookRepo.findById(bookEventDto.bookId()).orElseThrow();
        } catch (Exception e) {
            throw new BookException("Book with id " + bookEventDto.bookId() + " not found");
        }
        if(book.getStatus() != Status.AVAILABLE) {
            throw new BookException("Book with id " + bookEventDto.bookId() + " is not available");
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
            throw new PatronException("Patron with id " + bookEventDto.patronId() + " not found");
        } try {
            book = bookRepo.findById(bookEventDto.bookId()).orElseThrow();
        } catch (Exception e) {
            throw new BookException("Book with id " + bookEventDto.bookId() + " not found");
        }
        if(book.getStatus() != Status.CHECKED_OUT) {
            throw new BookException("Book with id " + bookEventDto.bookId() + " is not checked out");
        }
        BookEvent lastEvent = bookEventRepo.findBookEventByBookId(book.getId()).getLast();
        if(lastEvent == null || lastEvent.getType() != BookEventType.CHECKOUT) {
            throw new BookException("Book with id " + bookEventDto.bookId() + " is not checked out");
        } if(!lastEvent.getPatron().equals(patron)) {
            throw new PatronException("Patron with id " + bookEventDto.patronId() + " did not check out that book");
        }
        book.setStatus(Status.AVAILABLE);
        bookRepo.save(book);
        return bookEventRepo.save(new BookEvent(BookEventType.CHECKOUT, LocalDateTime.now(), book, patron));
    }

    public BookEvent findBookEventId(long bookEventId) {
        return bookEventRepo.findById(bookEventId).orElseThrow();
    }
}
