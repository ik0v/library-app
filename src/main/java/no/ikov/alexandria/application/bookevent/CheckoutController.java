package no.ikov.alexandria.application.bookevent;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final BookEventService bookEventService;

    public CheckoutController(BookEventService bookEventService) {
        this.bookEventService = bookEventService;
    }

    @GetMapping
    public ResponseEntity<List<BookEvent>> getAllBookEvents() {
        return ResponseEntity.ok(bookEventService.findAll());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<List<BookEvent>> getBookEventsByBookId(@PathVariable long bookId) {
        return ResponseEntity.ok(bookEventService.findBookEventsByBookId(bookId));
    }

    @GetMapping("/{bookEventId}")
    public ResponseEntity<BookEvent> getBookEventById(@PathVariable long bookEventId) {
        return ResponseEntity.ok(bookEventService.findBookEventId(bookEventId));
    }

    @PostMapping("checkout")
    public ResponseEntity<BookEvent> checkout(@RequestBody BookEventDto bookEventDto) {
        return new ResponseEntity<>(bookEventService.checkout(bookEventDto), HttpStatus.CREATED);
    }

    @PostMapping("/checkin")
    public ResponseEntity<BookEvent> checkin(@RequestBody BookEventDto bookEventDto) {
        return new ResponseEntity<>(bookEventService.checkIn(bookEventDto), HttpStatus.CREATED);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteBook(@PathVariable long id) {
//        bookEventService.deleteById(id);
//        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
//    }


}
