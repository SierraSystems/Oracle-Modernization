package com.nttdata.pocdata;

import com.nttdata.data.Contacts;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ContactsDao extends CrudRepository<Contacts, BigInteger> { }
