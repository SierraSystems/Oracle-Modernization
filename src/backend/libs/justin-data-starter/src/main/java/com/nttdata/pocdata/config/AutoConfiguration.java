package com.nttdata.pocdata.config;

import com.nttdata.pocdata.ContactsDao;
import com.nttdata.pocdata.ContactsService;
import com.nttdata.pocdata.ContactsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AutoConfiguration {

    //@Bean
    public ContactsService contactsService(ContactsDao contactsDao) {
        return new ContactsServiceImpl(contactsDao);
    }

}
