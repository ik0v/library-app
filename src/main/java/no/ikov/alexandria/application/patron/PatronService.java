package no.ikov.alexandria.application.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    private final PatronRepo patronRepo;

    @Autowired
    public PatronService(PatronRepo patronRepo) {
        this.patronRepo = patronRepo;
    }

    public List<Patron> findAll() {
        return patronRepo.findAll();
    }

    public Patron findById(long id) {
        return patronRepo.findById(id).orElse(null);
    }

    public Patron save(Patron patron) {
        return patronRepo.save(patron);
    }

    public void deleteById(long id) {
        patronRepo.deleteById(id);
    }
}
