package no.ikov.alexandria.application.bookevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookEventService {

    private final BookEventRepo bookEventRepo;

    @Autowired
    public BookEventService(BookEventRepo bookEventRepo) {
        this.bookEventRepo = bookEventRepo;
    }

    public List<BookEvent> findAll() {
        return bookEventRepo.findAll();
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
}
