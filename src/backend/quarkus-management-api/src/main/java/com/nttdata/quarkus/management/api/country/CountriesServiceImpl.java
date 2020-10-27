package com.nttdata.quarkus.management.api.country;

import io.quarkus.panache.common.Sort;

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
    public List<CountriesCache> getCountries() {
        return countriesRepository.findAll(Sort.ascending("COUNTRY_NAME")).list();
    }

    @Override
    public CountriesCache getCountry(String countryId) {
        return countriesRepository.findById(countryId);
    }

    @Override
    public CountriesCache updateCountry(CountriesCache countries) {
        this.countriesRepository.getEntityManager().merge(countries);
        return countries;
    }

    @Override
    public CountriesCache addCountry(CountriesCache countries) {
        countriesRepository.persist(countries);
        return countries;
    }

    @Override
    public void deleteCountry(String countryId) {
        countriesRepository.deleteById(countryId);
    }


}
