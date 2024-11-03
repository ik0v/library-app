package no.ikov.alexandria.application.patron;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.ikov.alexandria.application.bookevent.BookEvent;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patron_gen")
    @SequenceGenerator(name = "patron_gen", sequenceName = "patron_seq", allocationSize = 1)
    @Column(name = "patron_id", nullable = false)
    private long patronId;

    private String firstName;
    private String lastName;
    private LocalDate joinDate;

    public Patron(String firstName, String lastName, LocalDate joinDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinDate = joinDate;
    }

    @OneToMany(mappedBy = "patron")
    @JsonIgnoreProperties("patron")
    private List<BookEvent> events;

}
