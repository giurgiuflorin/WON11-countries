package org.fasttrackit.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.model.Country;
import org.fasttrackit.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("countries") // http://host:port/countries
public class CountryController {

    private final CountryService countryService;

    @GetMapping // GET http://host:port/countries
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    @GetMapping("/first") //countries/first
    public Country getFirst() {
        return countryService.getAllCountries().get(0);
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable long id) {
        return countryService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Country deleteById(@PathVariable long id) {

        return countryService.delete(id);
    }

    @PostMapping
    public Country addNewCountry(@RequestBody Country country) {
       return countryService.add(country);
    }

    @PutMapping("/{id}")
    public Country updateCountry(@RequestBody Country country, @PathVariable long id) {
        return countryService.update(country, id);
    }
}
