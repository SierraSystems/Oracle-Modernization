package com.nttdata.quarkus.management.api.dao;

import com.nttdata.quarkus.management.api.model.Contacts;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;

@ApplicationScoped
public interface ContactsDao extends PanacheRepositoryBase<Contacts, BigInteger> {
}
