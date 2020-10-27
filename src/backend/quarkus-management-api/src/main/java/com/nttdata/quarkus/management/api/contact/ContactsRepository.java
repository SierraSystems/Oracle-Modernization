package com.nttdata.quarkus.management.api.contact;

import com.nttdata.quarkus.management.api.model.database.Contacts;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.inject.Singleton;
import java.math.BigInteger;
import java.util.List;

@Singleton
public class ContactsRepository implements PanacheRepositoryBase<Contacts, BigInteger> {

    public List<Contacts> loadWithCustomer() {
       return this.find("from Contacts c left join fetch c.customers").list();
    }
}
