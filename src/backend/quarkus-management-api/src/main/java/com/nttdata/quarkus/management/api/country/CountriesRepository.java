package com.nttdata.quarkus.management.api.country;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.inject.Singleton;

@Singleton
public class CountriesRepository implements PanacheRepositoryBase<CountriesCache, String> {
}
