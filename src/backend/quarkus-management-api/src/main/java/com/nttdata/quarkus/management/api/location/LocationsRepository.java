package com.nttdata.quarkus.management.api.location;

import com.nttdata.quarkus.management.api.model.database.Locations;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.math.BigInteger;
import java.util.List;

public class LocationsRepository implements PanacheRepositoryBase<Locations, BigInteger> {

    public List<Locations> loadWithCountries() {
        return this.find("from Locations l left join fetch l.countries").list();
    }
}
