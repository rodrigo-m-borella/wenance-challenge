package com.wenance.challenge.argbitcoin.model.repository;

import com.wenance.challenge.argbitcoin.model.documet.BtcDaiDocument;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

//@Repository
public interface BtcDaiRepository extends ReactiveMongoRepository<BtcDaiDocument, String>, BtcDaiCustomRepository {
}
