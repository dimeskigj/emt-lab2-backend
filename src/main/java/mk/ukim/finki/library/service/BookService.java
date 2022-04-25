package mk.ukim.finki.library.service;

import mk.ukim.finki.library.domain.Author;
import mk.ukim.finki.library.domain.Book;
import mk.ukim.finki.library.domain.BookType;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    void deleteBookById(Long id) throws Exception;

    Book findById(Long id) throws Exception;

    Book saveBook(Book book);

    Book updateBook(Long id, String name, int copies, BookType category, Author author) throws Exception;

    Book takeBook(Long id) throws Exception;
}
