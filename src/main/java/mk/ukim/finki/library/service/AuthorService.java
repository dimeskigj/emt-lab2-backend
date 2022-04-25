package mk.ukim.finki.library.service;

import mk.ukim.finki.library.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author findById(Long id) throws Exception;

    Author saveAuthor(Author author);
}
