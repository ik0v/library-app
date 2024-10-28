package no.ikov.alexandria.application.patron;

import no.ikov.alexandria.application.patron.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getPatrons() {
        return ResponseEntity.ok(patronService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatron(@PathVariable long id) {
        return ResponseEntity.ok(patronService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Patron> createPatron(@RequestBody Patron patron) {
        return new ResponseEntity<>(patronService.save(patron), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(@PathVariable long id) {
        patronService.deleteById(id);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }

}
