package com.nttdata.quarkus.management.api.country;

import com.nttdata.pocdata.hibernate.Countries;

import java.util.List;

public interface CountriesService {
    List<Countries> getCountries();

    Countries getCountry(String countryId);

    Countries updateCountry(Countries countries);

    Countries addCountry(Countries countries);

    void deleteCountry(String countryId);
}
