package com.wenance.challenge.argbitcoin.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Getter
public class ArgBitcoinConfig {


	@Value("${bitcoin.data.market.base-url}")
	private String baseUrl;

	@Value("${bitcoin.data.market.end-point}")
	private String endPoint;

	@Value("${time-zone.id}")
	private String timeZone;

	@Value("${bitcoin.data.page.size}")
	private Integer pageSize;

	@Value("${bitcoin.data.market.time.lapse}")
	private Long timeLapse;


	@Bean
	WebClient registerWebClient() {
		return WebClient.create(baseUrl);
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}


}
