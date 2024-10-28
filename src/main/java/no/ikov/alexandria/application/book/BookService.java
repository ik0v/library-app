package no.ikov.alexandria.application.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo repo){
        this.bookRepo = repo;
    }

    public List<Book> findAll(){
        return bookRepo.findAll();
    }

    public Book findById(long id){
        return bookRepo.findById(id).orElse(null);
    }

    public Book save(Book book){
        return bookRepo.save(book);
    }

    public void deleteById(long id){
        bookRepo.deleteById(id);
    }
}
