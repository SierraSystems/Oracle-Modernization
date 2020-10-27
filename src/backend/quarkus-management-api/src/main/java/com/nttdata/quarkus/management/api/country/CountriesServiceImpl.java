package com.nttdata.quarkus.management.api.country;

import com.nttdata.quarkus.management.api.model.database.Countries;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CountriesServiceImpl implements CountriesService {

    private final CountriesRepository countriesRepository;

    @Inject
    public CountriesServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public List<Countries> getCountries() {
        return countriesRepository.loadWithRegions();
    }

    @Override
    public Countries getCountry(String countryId) {
        return countriesRepository.findById(countryId);
    }

    @Override
    public Countries updateCountry(Countries countries) {
        this.countriesRepository.getEntityManager().merge(countries);
        return countries;
    }

    @Override
    public Countries addCountry(Countries countries) {
        countriesRepository.persist(countries);
        return countries;
    }

    @Override
    public void deleteCountry(String countryId) {
        countriesRepository.deleteById(countryId);
    }


}
