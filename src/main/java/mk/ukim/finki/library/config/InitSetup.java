package mk.ukim.finki.library.config;

import mk.ukim.finki.library.domain.Author;
import mk.ukim.finki.library.domain.Book;
import mk.ukim.finki.library.domain.BookType;
import mk.ukim.finki.library.domain.Country;
import mk.ukim.finki.library.service.CountryService;
import mk.ukim.finki.library.service.implementation.AuthorService;
import mk.ukim.finki.library.service.implementation.BookService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class InitSetup {
    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public InitSetup(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    private void onInit() {
        /*
                                   _          _   _   _
                                  | |        | | | | (_)
             ___ _ __   __ _  __ _| |__   ___| |_| |_ _
            / __| '_ \ / _` |/ _` | '_ \ / _ \ __| __| |
            \__ \ |_) | (_| | (_| | | | |  __/ |_| |_| |
            |___/ .__/ \__,_|\__, |_| |_|\___|\__|\__|_|
                | |           __/ |
                |_|          |___/
         */
        String[] authorNames = {"John", "Bobby", "Sarah", "Karen", "Kaitlyn", "Jeb"};
        String[] authorSurnames = {"Smith", "Roberts", "King", "Lord"};
        String[] book1 = {"Path", "Trail", "Castle", "Love", "Animals", "Princess"};
        String[] book2 = {"of", "to", "and"};
        String[] book3 = {"Pain", "Love", "Happiness", "Sadness", "Depression"};
        List<Country> countryList = List.of(
                new Country(-1, "Macedonia", "Europe"),
                new Country(-1, "Canada", "North America"),
                new Country(-1, "Argentina", "South America"),
                new Country(-1, "Australia", "Oceania"),
                new Country(-1, "Japan", "Asia"),
                new Country(-1, "Ethiopia", "Africa"));
        countryList.forEach(countryService::saveCountry);
        List<Author> authorList = IntStream.range(1, 7).mapToObj(i ->
                {
                    try {
                        return authorService.saveAuthor(new Author(-1, authorNames[new Random().nextInt(authorNames.length)],
                                authorSurnames[new Random().nextInt(authorSurnames.length)], countryService.findById((long) i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
        ).collect(Collectors.toList());
        authorList.forEach(a -> bookService.saveBook(
                new Book(-1,
                        String.format("%s %s %s",
                                book1[new Random().nextInt(book1.length)],
                                book2[new Random().nextInt(book2.length)],
                                book3[new Random().nextInt(book3.length)]
                        ),
                        5, BookType.values()[new Random().nextInt(5)], a)
                )
        );
    }
}
