package com.wenance.challenge.argbitcoin.model.repository;

import com.wenance.challenge.argbitcoin.model.documet.BtcDaiDocument;
import reactor.core.publisher.Flux;

import org.springframework.data.domain.Pageable;
import java.time.Instant;

public interface BtcDaiCustomRepository {
	Flux<BtcDaiDocument> getBtcDaiDocumentsBetweenDates(Instant fromDate, Instant toDate);

	Flux<BtcDaiDocument> getPaginatedBtcDaiDocumentsBetweenDates(Instant fromDate, Instant toDate, Pageable page);

	Flux<BtcDaiDocument> getPaginatedBtcDaiDocuments(Pageable page);

}
