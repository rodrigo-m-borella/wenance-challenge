package com.wenance.challenge.argbitcoin.model.documet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Getter
@Setter
public class BtcDaiDetailDocument {

	private String currency;
	private String bidCurrency;
	private String askCurrency;
	private String purchasePrice;
	private String sellingPrice;
	private String marketIdentifier;
}
