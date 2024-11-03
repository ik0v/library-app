package no.ikov.alexandria.application.bookevent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookEventRepo extends JpaRepository<BookEvent, Long> {

    List<BookEvent> findBookEventByBookId(long bookId);
}
