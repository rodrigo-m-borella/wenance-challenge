package com.wenance.challenge.argbitcoin.service;

import com.wenance.challenge.argbitcoin.domain.dto.ArgBitcoinDTO;
import com.wenance.challenge.argbitcoin.domain.dto.BuenbitObjectResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface ArgBitcoinService {

	Mono<ArgBitcoinDTO> getArgBitcoinSellingPrice(LocalDateTime date);

	Mono<ArgBitcoinDTO> getArgBitcoinAverageSellingPrice(LocalDateTime initialDate, LocalDateTime finalDate);

	Flux<BuenbitObjectResponseDTO> getCryptoCurrencyData(String fromDate, String toDate, int page);
}

