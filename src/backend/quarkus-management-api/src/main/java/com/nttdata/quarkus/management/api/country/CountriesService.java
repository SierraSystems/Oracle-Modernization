package com.nttdata.quarkus.management.api.country;

import java.util.List;

public interface CountriesService {
    List<CountriesCache> getCountries();

    CountriesCache getCountry(String countryId);

    CountriesCache updateCountry(CountriesCache countries);

    CountriesCache addCountry(CountriesCache countries);

    void deleteCountry(String countryId);
}
