package no.ikov.alexandria.application.book;

import no.ikov.alexandria.application.author.Author;
import no.ikov.alexandria.application.author.AuthorService;
import no.ikov.alexandria.application.bookevent.BookEvent;
import no.ikov.alexandria.application.bookevent.BookEventRepo;
import no.ikov.alexandria.application.bookevent.BookEventService;
import no.ikov.alexandria.application.bookevent.BookEventType;
import no.ikov.alexandria.application.location.Location;
import no.ikov.alexandria.application.location.LocationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final LocationService locationService;
    private final AuthorService authorService;
    private final BookEventRepo bookEventRepo;

    public BookService(BookRepo repo, LocationService locationService, AuthorService authorService, BookEventService bookEventService, BookEventRepo bookEventRepo){
        this.bookRepo = repo;
        this.locationService = locationService;
        this.authorService = authorService;
        this.bookEventRepo = bookEventRepo;
    }

    public List<Book> findAll(){
        return bookRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(Book::getId))
                .toList();
    }

    public Book findById(long id){
        return bookRepo.findById(id).orElse(null);
    }

//    @Transactional
    public Book save(BookDto bookDto) {
        Location location;
        try {
            location = locationService.findById(bookDto.getLocationId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Author> bookAuthors = authorService.getAuthors()
                .stream()
                .filter(a -> bookDto.getAuthorIds().contains(a.getId()))
                .toList();

        Book book = bookRepo.save(new Book(
                bookDto.getTitle(),
                bookDto.getPublisher(),
                Status.AVAILABLE,
                bookAuthors,
                location
        ));

        BookEvent event = bookEventRepo.save(
                new BookEvent(
                        BookEventType.ACQUIRE,
                        LocalDateTime.now(),
                        book,
                        null
                )
        );
        return bookRepo.save(book);
    }

    public void deleteById(long id){
        bookRepo.deleteById(id);
    }

}
