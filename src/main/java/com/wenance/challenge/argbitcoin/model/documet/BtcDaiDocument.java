package com.wenance.challenge.argbitcoin.model.documet;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "btcdai")
@Getter
@Setter
public class BtcDaiDocument {

	@Id
	private String id;
	private BtcDaiDetailDocument daiars;
	private BtcDaiDetailDocument daiusd;
	private BtcDaiDetailDocument btcars;
	private Instant date;

	public BtcDaiDocument(){
		this.daiars = new BtcDaiDetailDocument();
		this.daiusd = new BtcDaiDetailDocument();
		this.btcars = new BtcDaiDetailDocument();
	}
}
