package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.domain.Author;
import mk.ukim.finki.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements mk.ukim.finki.library.service.AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) throws Exception {
        return authorRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
}
