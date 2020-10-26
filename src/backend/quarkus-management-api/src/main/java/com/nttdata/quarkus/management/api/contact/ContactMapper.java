package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.openapi.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface ContactMapper {

    @Mapping(target = "phone", source = "phoneNumber")
    Contacts toContacts(Contact contact);

    @Mapping(target = "customerId", source = "customers.customerId")
    @Mapping(target = "phoneNumber", source = "phone")
    @Mapping(target = "customerName", source = "customers.name")
    Contact toContact(Contacts contacts);

}
