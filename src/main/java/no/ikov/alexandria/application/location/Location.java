package no.ikov.alexandria.application.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import no.ikov.alexandria.application.book.Book;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_gen")
    @SequenceGenerator(name = "location_gen", sequenceName = "location_seq", allocationSize = 1)
    @Column(name = "location_id", nullable = false)
    private long location_id;
    private String shelf;

    @OneToMany(mappedBy = "location")
    @JsonIgnoreProperties("location")
    private List<Book> books;

    public Location(String shelf) {
        this.shelf = shelf;
    }

}
