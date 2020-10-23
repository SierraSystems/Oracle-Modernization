package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.model.database.Customers;
import com.nttdata.quarkus.management.api.openapi.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "cdi")
public interface ContactMapper {

    @Mapping(target = "phone", source = "phoneNumber")
    @Mapping(target = "customers", source = "customerId", qualifiedByName = "toCustomers")
    Contacts toContacts(Contact contact);

    @Mapping(target = "customerId", source = "customers.customerId")
    @Mapping(target = "phoneNumber", source = "phone")
    Contact toContact(Contacts contacts);

    @Named("toCustomers")
    static Customers toCustomers(BigDecimal customerId) {
        return Customers.findById(customerId.toBigInteger());
    }

}