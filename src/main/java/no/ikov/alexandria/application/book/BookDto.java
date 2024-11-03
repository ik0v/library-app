package no.ikov.alexandria.application.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class BookDto {
    private String title;
    private String publisher;
    private long locationId;
    private List<Long> authorIds;

    public BookDto(String title, String publisher, long locationId, List<Long> authorIds) {
        this.title = title;
        this.publisher = publisher;
        this.locationId = locationId;
        this.authorIds = authorIds;
    }

}

