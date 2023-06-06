package org.fasttrackit.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.exception.ResourcesNotFound;
import org.fasttrackit.model.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryReader countryReader;

    private final CountryRepository countryRepository;
    private final List<Country> countryList = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("Post construct in Country Service");
        List<Country> countries = countryReader.readCountries();
        countryRepository.saveAll(countries);
        countryList.addAll(countryReader.readCountries());
    }

    public List<Country> getAllCountries() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false).toList();
    }

    public List<Country> getByContinent(String continent) {
        return getAllCountries().stream()
                .filter(country -> country.getContinent().equalsIgnoreCase(continent))
                .toList();
    }

    public Country getById(long id) {

        return getAllCountries().stream()
                .filter(country -> country.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResourcesNotFound("country not found", -1));
    }

    public Country delete(long id) {
        Country country = getById(id);
        countryList.remove(country);
        return country;
    }

    public Country add(Country country) {
        countryList.add(country);
        return country;
    }

    public Country update(Country country, long id) {

        Country existingCountry = delete(id);
        delete(id);
        return add(Country.builder()
                .id(id)
                .name(existingCountry.getName())
                .neighbours(existingCountry.getNeighbours())
                .capital(country.getCapital())
                .population(country.getPopulation())
                .area(country.getArea())
                .continent(existingCountry.getContinent())
                .build());
    }
}
