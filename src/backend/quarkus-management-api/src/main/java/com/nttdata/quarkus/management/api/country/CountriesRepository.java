package com.nttdata.quarkus.management.api.country;

import com.nttdata.quarkus.management.api.model.database.Countries;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CountriesRepository implements PanacheRepositoryBase<Countries, String> {
    public List<Countries> loadWithRegions() {
        return this.find("from Countries c left join fetch c.regions", Sort.by("COUNTRY_NAME")).list();
    }
}
