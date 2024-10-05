package no.ikov.alexandria.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @NotBlank(message = "Section cannot be blank")
    private String section;
    @NotBlank(message = "Shelf cannot be blank")
    private String shelf;
}
