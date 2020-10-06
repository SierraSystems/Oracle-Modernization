package com.nttdata.managementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.nttdata.managementapi","com.nttdata.pocdata"})
@EnableTransactionManagement
@EnableJpaRepositories("com.nttdata.pocdata")
public class ManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApiApplication.class, args);
	}

}
