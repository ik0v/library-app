package no.ikov.alexandria.application;

import no.ikov.alexandria.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository repo;

    public LibraryService(LibraryRepository repo) {
        this.repo = repo;
    }

    public List<Book> getBooks() {
        return repo.getBooks();
    }

    public List<Book> getBooksByAuthor(String author) {
        return repo.getBooksByAuthor(author);
    }

    public List<Book> getAvailableBooks() {
        return repo.getAvailableBooks();
    }

    public Book getBookById(int id) {
        return repo.getBookById(id);
    }

    public Book addNewBook(Book newBook) {
        return repo.addNewBook(newBook);
    }
}
