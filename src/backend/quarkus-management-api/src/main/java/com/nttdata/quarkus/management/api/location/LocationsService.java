package com.nttdata.quarkus.management.api.location;

import com.nttdata.pocdata.hibernate.Locations;

import java.math.BigInteger;
import java.util.List;

public interface LocationsService {
    List<Locations> getLocations();

    Locations getLocation(BigInteger locationsId);

    Locations updateLocation(Locations locations);

    Locations addLocation(Locations locations);

    void deleteLocation(BigInteger locationId);
}
