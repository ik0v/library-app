package no.ikov.alexandria.application;

import com.github.javafaker.Faker;
import no.ikov.alexandria.exceptions.BookNotFoundException;
import no.ikov.alexandria.models.Author;
import no.ikov.alexandria.models.Book;
import no.ikov.alexandria.models.Location;
import no.ikov.alexandria.models.Status;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibraryRepository {

    private final List<Book> library;

    public LibraryRepository() {
        library = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 1; i <= 50; i++) {
            library.add(new Book(
                    i,
                    Faker.instance().book().title(),
                    new Author(faker.name().firstName(), faker.name().lastName()),
                    faker.book().publisher(),
                    new Status(faker.random().nextBoolean()),
                    new Location(faker.letterify("Section ?").toUpperCase(), faker.numerify("Shelf ##"))
            ));
        }
    }

    public List<Book> getBooks() {
        return library;
    }

    public List<Book> getBooksByAuthor(String author) {
        String firstName = author.substring(0, author.indexOf(" "));
        String lastName = author.substring(author.indexOf(" ") + 1);
        return library
                .stream()
                .filter(b -> b.getAuthor().getFirstName().equals(firstName) &&
                            b.getAuthor().getLastName().equals(lastName))
                .toList();
    }

    public List<Book> getAvailableBooks() {
        return library
                .stream()
                .filter(b -> b.getStatus().isAvailable())
                .toList();
    }

    public Book getBookById(int id) {
        Book book = library
                .stream()
                .filter(b -> b.getBookId() == id)
                .findFirst()
                .orElse(null);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    public Book addNewBook(Book newBook) {
        newBook.setBookId(library.size()+1);
        library.add(newBook);
        return getBookById(library.size());
    }

    public Book changeStatusById(int id, Status newStatus) {
        Book book = getBookById(id);
        book.setStatus(new Status(newStatus.isAvailable()));
        return book;
    }

}
