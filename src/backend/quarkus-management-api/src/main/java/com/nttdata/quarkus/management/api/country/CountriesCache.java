package com.nttdata.quarkus.management.api.country;

import com.nttdata.pocdata.hibernate.Countries;

import javax.persistence.Cacheable;

@Cacheable
public class CountriesCache extends Countries {
}
