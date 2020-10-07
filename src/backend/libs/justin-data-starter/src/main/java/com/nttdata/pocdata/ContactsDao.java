package com.nttdata.pocdata;

import com.nttdata.pocdata.hibernate.Contacts;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Transactional
public interface ContactsDao extends CrudRepository<Contacts, BigInteger> { }
