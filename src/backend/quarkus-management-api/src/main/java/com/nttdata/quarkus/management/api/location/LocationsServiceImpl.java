package com.nttdata.quarkus.management.api.location;

import com.nttdata.pocdata.hibernate.Locations;

import javax.inject.Inject;
import java.math.BigInteger;
import java.util.List;

public class LocationsServiceImpl implements LocationsService {

    private final LocationsRepository locationsRepository;

    @Inject
    public LocationsServiceImpl(LocationsRepository locationsRepository) {
        this.locationsRepository = locationsRepository;
    }

    @Override
    public List<Locations> getLocations() {
        return locationsRepository.loadWithCountries();
    }

    @Override
    public Locations getLocation(BigInteger locationsId) {
        return locationsRepository.loadWithCountries()
                .stream()
                .filter(locations -> locations.getLocationId().equals(locationsId))
                .findFirst()
                .get();
    }

    @Override
    public Locations updateLocation(Locations locations) {
        locationsRepository.getEntityManager().merge(locations);
        return locations;
    }

    @Override
    public Locations addLocation(Locations locations) {
        locationsRepository.persist(locations);
        return locations;
    }

    @Override
    public void deleteLocation(BigInteger locationId) {
        locationsRepository.deleteById(locationId);
    }
}
