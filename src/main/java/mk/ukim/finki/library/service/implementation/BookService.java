package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.domain.Author;
import mk.ukim.finki.library.domain.Book;
import mk.ukim.finki.library.domain.BookType;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements mk.ukim.finki.library.service.BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Override
    public void deleteBookById(Long id) throws Exception {
        bookRepository.delete(bookRepository.findById(id).orElseThrow(Exception::new));
    }

    @Override
    public Book findById(Long id) throws Exception {
        return bookRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, String name, int copies, BookType category, Author author) throws Exception {
        Book b = bookRepository.findById(id).orElseThrow(Exception::new);
        b.setName(name);
        b.setCopies(copies);
        b.setCategory(category);
        b.setAuthor(author);
        return bookRepository.save(b);
    }

    @Override
    public Book takeBook(Long id) throws Exception {
        Book b = bookRepository.findById(id).orElseThrow(Exception::new);
        b.takeBook();
        return bookRepository.save(b);
    }
}
