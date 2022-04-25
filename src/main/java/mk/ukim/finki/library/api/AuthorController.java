package mk.ukim.finki.library.api;

import mk.ukim.finki.library.domain.Author;
import mk.ukim.finki.library.domain.Book;
import mk.ukim.finki.library.domain.requests.AuthorRequest;
import mk.ukim.finki.library.service.CountryService;
import mk.ukim.finki.library.service.implementation.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/author")
public class AuthorController {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostMapping(path = "/add")
    public Author addAuthor(@RequestBody AuthorRequest request) throws Exception {
        Author a = new Author();
        a.setName(request.name);
        a.setSurname(request.surname);
        a.setCountry(countryService.findById(request.countryId));
        return authorService.saveAuthor(a);
    }

    @GetMapping(path = "/all")
    public List<Author> findAll() {
        return authorService.findAll();
    }
}
