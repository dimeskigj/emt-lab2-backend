package mk.ukim.finki.library.service;

import mk.ukim.finki.library.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll();

    Country findById(Long id) throws Exception;

    Country saveCountry(Country country);
}
