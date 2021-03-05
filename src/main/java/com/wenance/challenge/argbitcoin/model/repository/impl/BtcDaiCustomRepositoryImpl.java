package com.wenance.challenge.argbitcoin.model.repository.impl;

import com.wenance.challenge.argbitcoin.model.documet.BtcDaiDocument;
import com.wenance.challenge.argbitcoin.model.repository.BtcDaiCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import org.springframework.data.domain.Pageable;
import java.time.Instant;

public class BtcDaiCustomRepositoryImpl implements BtcDaiCustomRepository {

	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;

	@Override
	public Flux<BtcDaiDocument> getBtcDaiDocumentsBetweenDates(Instant fromDate, Instant toDate) {
		final Query query = new Query();


		query.addCriteria(
				Criteria.where("date").gte(fromDate)
						.andOperator(
								Criteria.where("date").lt(toDate)));


		return reactiveMongoTemplate.find(query, BtcDaiDocument.class);
	}

	@Override
	public Flux<BtcDaiDocument> getPaginatedBtcDaiDocumentsBetweenDates(Instant fromDate, Instant toDate, Pageable page) {
		final Query query = new Query().with(page);


		query.addCriteria(
				Criteria.where("date").gte(fromDate)
						.andOperator(
								Criteria.where("date").lt(toDate)));


		return reactiveMongoTemplate.find(query, BtcDaiDocument.class);
	}

	@Override
	public Flux<BtcDaiDocument> getPaginatedBtcDaiDocuments(Pageable page) {
		final Query query = new Query().with(page);
		return reactiveMongoTemplate.find(query, BtcDaiDocument.class);
	}
}
