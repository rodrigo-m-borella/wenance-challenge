package com.wenance.challenge.argbitcoin.service.impl;

import com.wenance.challenge.argbitcoin.config.ArgBitcoinConfig;
import com.wenance.challenge.argbitcoin.domain.dto.ArgBitcoinDTO;
import com.wenance.challenge.argbitcoin.domain.dto.BuenbitObjectResponseDTO;
import com.wenance.challenge.argbitcoin.domain.mapper.SourceToDestinationMapper;
import com.wenance.challenge.argbitcoin.model.documet.BtcDaiDocument;
import com.wenance.challenge.argbitcoin.model.repository.BtcDaiRepository;
import com.wenance.challenge.argbitcoin.service.ArgBitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("argBitcoinService")
public class ArgBitcoinServiceImpl implements ArgBitcoinService {
	@Autowired
	BtcDaiRepository btcDaiRepository;

	@Autowired
	ArgBitcoinConfig argBitcoinConfig;

	@Autowired
	private SourceToDestinationMapper sourceToDestinationMapper;


	@Override
	public Mono<ArgBitcoinDTO> getArgBitcoinSellingPrice(LocalDateTime date) {

		Flux<BtcDaiDocument> btcDaiDocuments = btcDaiRepository.getBtcDaiDocumentsBetweenDates(
				getUTCInstant(date.minusSeconds(argBitcoinConfig.getTimeLapse())), getUTCInstant(date));

		return btcDaiDocuments.next().map(d -> new ArgBitcoinDTO(Double.parseDouble(d.getBtcars().getSellingPrice())));
	}

	@Override
	public Mono<ArgBitcoinDTO> getArgBitcoinAverageSellingPrice(LocalDateTime fromDate, LocalDateTime toDate) {

		Flux<BtcDaiDocument> btcDaiDocuments = btcDaiRepository.getBtcDaiDocumentsBetweenDates(getUTCInstant(fromDate), getUTCInstant(toDate));

		Mono<Double> avg = btcDaiDocuments.collect(Collectors.averagingDouble(d -> Double.parseDouble(d.getBtcars().getSellingPrice())));

		return avg.map(ArgBitcoinDTO::new);
	}

	@Override
	public Flux<BuenbitObjectResponseDTO> getCryptoCurrencyData(String fromDate, String toDate, int page) {

		if (StringUtils.hasLength(fromDate)) {
			return btcDaiRepository.getPaginatedBtcDaiDocumentsBetweenDates(getUTCInstant(LocalDateTime.parse(fromDate)),
					getUTCInstant(LocalDateTime.parse(toDate)), PageRequest.of(page, argBitcoinConfig.getPageSize()))
					.map(r -> sourceToDestinationMapper.btcDaiDocumentDTOToBuenbitObjectResponseDTO(r));
		} else {
			return btcDaiRepository.getPaginatedBtcDaiDocuments(PageRequest.of(page, argBitcoinConfig.getPageSize()))
					.map(r -> sourceToDestinationMapper.btcDaiDocumentDTOToBuenbitObjectResponseDTO(r));
		}
	}

	private Instant getUTCInstant(LocalDateTime zonedLocalDateTime) {
		return zonedLocalDateTime.atZone(ZoneId.of(argBitcoinConfig.getTimeZone()))
				.withZoneSameInstant(ZoneId.of("UTC"))
				.toLocalDateTime()
				.toInstant(ZoneOffset.UTC);
	}
}
