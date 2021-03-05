package com.wenance.challenge.argbitcoin;

import com.wenance.challenge.argbitcoin.service.CryptoCurrencyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ArgbitcoinApplication implements CommandLineRunner {

	@Autowired
	CryptoCurrencyDataService cryptoCurrencyDataService;

	public static void main(String[] args) {
		SpringApplication.run(ArgbitcoinApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cryptoCurrencyDataService.cryptoCurrencyDataLoad();
	}
}
