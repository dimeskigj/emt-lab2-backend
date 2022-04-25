package mk.ukim.finki.library.service.implementation;

import mk.ukim.finki.library.domain.Country;
import mk.ukim.finki.library.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements mk.ukim.finki.library.service.CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) throws Exception {
        return countryRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }
}
