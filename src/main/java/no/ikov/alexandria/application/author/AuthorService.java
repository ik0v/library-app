package no.ikov.alexandria.application.author;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public List<Author> getAuthors() {
        return authorRepo.findAll();
    }

    public Author getAuthor(long id) {
        return authorRepo.findById(id).orElse(null);
    }

    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }

    public void deleteAuthorById(long id) {
        authorRepo.deleteById(id);
    }
}
