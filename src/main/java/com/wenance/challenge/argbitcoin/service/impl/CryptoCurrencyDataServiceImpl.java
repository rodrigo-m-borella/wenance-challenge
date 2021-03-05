package com.wenance.challenge.argbitcoin.service.impl;

import com.wenance.challenge.argbitcoin.config.ArgBitcoinConfig;
import com.wenance.challenge.argbitcoin.domain.dto.BuenbitResponseDTO;
import com.wenance.challenge.argbitcoin.domain.mapper.SourceToDestinationMapper;
import com.wenance.challenge.argbitcoin.model.documet.BtcDaiDocument;
import com.wenance.challenge.argbitcoin.model.repository.BtcDaiRepository;
import com.wenance.challenge.argbitcoin.service.CryptoCurrencyDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.*;

@Service("cryptoCurrencyDataService")
@Slf4j
public class CryptoCurrencyDataServiceImpl implements CryptoCurrencyDataService {

	@Autowired
	private WebClient client;

	@Autowired
	private BtcDaiRepository btcDaiRepository;

	@Autowired
	private ArgBitcoinConfig argBitcoinConfig;

	@Autowired
	private SourceToDestinationMapper sourceToDestinationMapper;


	@Override
	public void cryptoCurrencyDataLoad() {
		Flux<BuenbitResponseDTO> buenBitResponse = client.get()
				.uri(argBitcoinConfig.getEndPoint())
				.accept(MediaType.APPLICATION_JSON)
				.exchangeToMono(clientResponse -> clientResponse.bodyToMono(BuenbitResponseDTO.class))
				.onErrorContinue(this::logAndWait)
				.flatMap(buenbitResponseDTO -> {
					BtcDaiDocument btcDaiDocument = sourceToDestinationMapper.buenbitResponseDTOToBtcDaiDocument(buenbitResponseDTO);
					btcDaiDocument.setDate(Instant.now());
					btcDaiRepository.save(btcDaiDocument).subscribe();
					return Mono.just(buenbitResponseDTO);
				})
				.delayElement(Duration.ofSeconds(argBitcoinConfig.getTimeLapse()))
				.repeat();
		buenBitResponse.subscribe();
	}

	private void logAndWait(Throwable e, Object o) {
		log.info("An error occurred while trying to fetch data from external server. {}", e.getLocalizedMessage());
		try {
			Thread.sleep(60000);
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
		cryptoCurrencyDataLoad();
	}
}
