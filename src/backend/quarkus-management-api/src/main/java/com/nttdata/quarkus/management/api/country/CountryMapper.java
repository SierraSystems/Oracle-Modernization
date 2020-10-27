package com.nttdata.quarkus.management.api.country;

import com.nttdata.quarkus.management.api.model.database.Countries;
import com.nttdata.quarkus.management.api.model.database.Regions;
import com.nttdata.quarkus.management.api.openapi.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "cdi")
public interface CountryMapper {

    @Mapping(target = "regions", source = "regionId", qualifiedByName = "toRegions")
    Countries toCountries(Country country);

    @Mapping(target = "regionId", source = "regions.regionId")
    Country toCountry(Countries countries);

    @Named("toRegions")
    static Regions toRegions(BigDecimal regionId) {
        Regions regions = new Regions();
        regions.setRegionId(regionId.toBigInteger());
        return regions;
    }


}
