package no.ikov.alexandria.application.bookevent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookEventRepo extends JpaRepository<BookEvent, Long> {
}
