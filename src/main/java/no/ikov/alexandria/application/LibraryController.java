package no.ikov.alexandria.application;

import jakarta.validation.Valid;
import no.ikov.alexandria.models.Book;
import no.ikov.alexandria.models.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = libraryService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = libraryService.getAvailableBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/byAuthor/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = libraryService.getBooksByAuthor(author);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = libraryService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Book> addBook(@RequestBody @Valid Book newBook) {
        if (newBook.getStatus() == null) {
            newBook.setStatus(new Status());  // Ensure status is set to default
        }
        return new ResponseEntity<>(libraryService.addNewBook(newBook), HttpStatus.CREATED);
    }


}
