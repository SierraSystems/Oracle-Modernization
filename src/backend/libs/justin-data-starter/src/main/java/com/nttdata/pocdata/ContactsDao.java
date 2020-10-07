package com.nttdata.pocdata;

import com.nttdata.pocdata.hibernate.Contacts;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ContactsDao extends CrudRepository<Contacts, BigInteger> { }
