package mk.ukim.finki.library.api;

import mk.ukim.finki.library.domain.Country;
import mk.ukim.finki.library.domain.requests.CountryRequest;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/country")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(path = "/add")
    public Country addCountry(@RequestBody CountryRequest request) {
        Country c = new Country();
        c.setName(request.name);
        c.setContinent(request.continent);
        return countryService.saveCountry(c);
    }
}
