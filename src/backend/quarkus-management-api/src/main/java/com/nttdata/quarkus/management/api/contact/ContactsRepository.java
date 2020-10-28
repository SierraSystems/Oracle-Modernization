package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.inject.Singleton;
import java.math.BigInteger;
import java.util.List;

@Singleton
public class ContactsRepository implements PanacheRepositoryBase<Contacts, BigInteger> {

    public static final String BASE_QUERY = "select new com.nttdata.quarkus.management.api.contact.ContactDto(" +
            "ctc.contactId, " +
            "ctc.firstName, " +
            "ctc.lastName, " +
            "ctc.email, " +
            "ctc.phone, " +
            "cus.customerId, " +
            "cus.name) " +
            "from Contacts ctc join ctc.customers cus";

    public List<ContactDto> getContactFromCursor(BigInteger contactId, int limit) {

        PanacheQuery panacheQuery;

        if(contactId == BigInteger.ZERO) {
            panacheQuery = this.find(BASE_QUERY, Sort.descending("CONTACT_ID"));
        } else {
            panacheQuery = this
                    .find(BASE_QUERY + " where ctc.contactId <= " + contactId.toString(), Sort.descending("CONTACT_ID"));
        }


        List<ContactDto> contacts = panacheQuery.page(Page.ofSize(limit)).list();

        return contacts;


    }
}
