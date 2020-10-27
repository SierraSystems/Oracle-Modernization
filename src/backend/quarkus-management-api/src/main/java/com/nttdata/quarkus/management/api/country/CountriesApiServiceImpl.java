package com.nttdata.quarkus.management.api.country;

import com.nttdata.pocdata.hibernate.Contacts;

import com.nttdata.quarkus.management.api.openapi.CountriesApi;
import com.nttdata.quarkus.management.api.openapi.model.Contact;
import com.nttdata.quarkus.management.api.openapi.model.Country;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.math.BigDecimal;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class CountriesApiServiceImpl implements CountriesApi {

    private static final Logger logger = Logger.getLogger(String.valueOf(CountriesApiServiceImpl.class));


    private final CountriesService countriesService;

    private final CountryMapper countryMapper;

    @Inject
    public CountriesApiServiceImpl(CountriesService countriesService, CountryMapper countryMapper) {
        this.countriesService = countriesService;
        this.countryMapper = countryMapper;
    }


    @Override
    @Transactional
    public Response addCountry(@Valid Country country, SecurityContext securityContext) {

        logger.info("Add country");

        CountriesCache countryToAdd = countryMapper.toCountries(country);

        return Response.ok(countryMapper.toCountry(countriesService.addCountry(countryToAdd))).status(201).build();

    }

    @Override
    @Transactional
    public Response deleteCountry(String countryId, SecurityContext securityContext) {

        logger.info("Delete country ");
        countriesService.deleteCountry(countryId);
        return Response.ok().build();

    }

    @Override
    @Transactional
    public Response getCountry(String countryId, SecurityContext securityContext) {

        logger.info("Get country");
        return Response.ok(countryMapper.toCountry(countriesService.getCountry(countryId))).build();

    }

    @Override
    @Transactional
    public Response getCountries(SecurityContext securityContext) {

        logger.info("get all countries ");
        return Response.ok(
                countriesService
                        .getCountries()
                        .stream()
                        .map(countryMapper::toCountry)
                        .collect(Collectors.toList())).build();

    }

    @Override
    @Transactional
    public Response updateCountry(@Valid Country country, SecurityContext securityContext) {

        logger.info("Update country");
        return Response.ok(
                countryMapper.toCountry(
                        countriesService.updateCountry(countryMapper.toCountries(country))))
                .status(200).build();

    }
}
