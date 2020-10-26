package com.nttdata.quarkus.management.api.country;

import com.nttdata.pocdata.hibernate.Countries;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.inject.Singleton;

@Singleton
public class CountriesRepository implements PanacheRepositoryBase<Countries, String> {
}
