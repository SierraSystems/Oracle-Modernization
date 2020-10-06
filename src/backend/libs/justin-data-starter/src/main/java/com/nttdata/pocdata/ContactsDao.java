package com.nttdata.pocdata;

import com.nttdata.data.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;


import java.math.BigDecimal;

public interface ContactsDao extends JpaRepository<Contacts, BigDecimal> { }
