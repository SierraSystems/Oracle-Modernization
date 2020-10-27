package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import com.nttdata.quarkus.management.api.queryUtils.CursorResultSet;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Singleton;
import java.math.BigInteger;
import java.util.List;

@Singleton
public class ContactsRepository implements PanacheRepositoryBase<Contacts, BigInteger> {

    public CursorResultSet<Contacts> loadWithCustomer(String value, int limit) {

        int queryLimit = limit + 1;
        String lastId = "";

      //  BigInteger userId = new BigInteger(value);

        PanacheQuery<Contacts> panacheQuery;

        if(StringUtils.isBlank(value)) {
            panacheQuery = this.find("from Contacts c left join fetch c.customers", Sort.descending("CONTACT_ID"));
        } else {
            panacheQuery = this
                    .find("from Contacts c left join fetch c.customers where c.contactId <= " + value, Sort.descending("CONTACT_ID"));
        }

        List<Contacts> contacts = panacheQuery.page(Page.ofSize(queryLimit)).list();

        if(contacts.size() == queryLimit) {
            lastId = contacts.get(limit).getContactId().toString();
            contacts.remove(limit);
        }

        return CursorResultSet.builder().items(contacts).nextCursor(lastId).create();

    }
}
