package no.ikov.alexandria.application.book;

import no.ikov.alexandria.application.author.Author;
import no.ikov.alexandria.application.author.AuthorService;
import no.ikov.alexandria.application.location.Location;
import no.ikov.alexandria.application.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final LocationService locationService;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepo repo, LocationService locationService, AuthorService authorService){
        this.bookRepo = repo;
        this.locationService = locationService;
        this.authorService = authorService;
    }

    public List<Book> findAll(){
        return bookRepo.findAll();
    }

    public Book findById(long id){
        return bookRepo.findById(id).orElse(null);
    }

    public Book save(BookDto bookDto) {
        Location location = null;
        try {
            location = locationService.findById(bookDto.locationId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Author> bookAuthors = authorService.getAuthors()
                .stream()
                .filter(a -> bookDto.authors().contains(a.getId()))
                .toList();

        Book book = new Book(
                bookDto.title(),
                bookDto.publisher(),
                Status.AVAILABLE,
                bookAuthors,
                location
        );
        return bookRepo.save(book);
    }

    public void deleteById(long id){
        bookRepo.deleteById(id);
    }
}
