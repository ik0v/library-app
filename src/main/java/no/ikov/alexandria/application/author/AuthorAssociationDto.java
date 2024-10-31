package no.ikov.alexandria.application.author;

public record AuthorAssociationDto(
        long bookId,
        long authorId
) {
}
