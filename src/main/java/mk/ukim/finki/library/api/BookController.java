package mk.ukim.finki.library.api;

import mk.ukim.finki.library.domain.Book;
import mk.ukim.finki.library.domain.BookType;
import mk.ukim.finki.library.domain.requests.BookEditRequest;
import mk.ukim.finki.library.domain.requests.BookRequest;
import mk.ukim.finki.library.service.CountryService;
import mk.ukim.finki.library.service.implementation.AuthorService;
import mk.ukim.finki.library.service.implementation.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Оваа апликација треба да има сличен изглед како апликацијата од аудиториски вежби, односно:
//
//        - На почетната страна се прикажани сите книги (може да се видат на патека / и /books)
//            o За секоја книга овозможено е копче Edit, Delete, Mark As Taken
//            o На клик на копчињата Edit/ Delete се прави повик кон API и соодветно да се
//            измени/ избрише книгата
//            o На клик на копчето Mark As Taken, потребно е да се направи повик кон API и
//            соодветно да се намали бројот на availableCopies
//            o Дополнително има едно копче Add a new book, со кое се овозможува додавање
//            на нова книга
//        - На друга страна се прикажани сите категории (може да се видат на патека /categories)
//        - Постои header дел кој всушност е мени за навигација


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = {"/api/book"})
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public BookController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Book> addBook(@RequestBody BookRequest request) {
        try {
            Book b = new Book();
            b.setName(request.name);
            b.setCopies(request.copies);
            b.setCategory(request.category);
            b.setAuthor(authorService.findById(request.authorId));
            return ResponseEntity.ok(bookService.saveBook(b));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<Book> editBook(@RequestBody BookEditRequest request) {
        try {
            Book b = bookService.findById(request.getId());
            b.setName(request.getName());
            b.setCopies(request.getCopies());
            b.setCategory(request.getCategory());
            b.setAuthor(authorService.findById(request.getAuthorId()));
            return ResponseEntity.ok(bookService.saveBook(b));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBookById(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/take/{id}")
    public ResponseEntity<Book> takeBook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.takeBook(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/categories")
    public BookType[] getCategories() {
        return BookType.values();
    }
}
