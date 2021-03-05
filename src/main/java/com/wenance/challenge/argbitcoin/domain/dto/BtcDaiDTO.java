package com.wenance.challenge.argbitcoin.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BtcDaiDTO {

	private String currency;
	@JsonProperty("bid_currency")
	private String bidCurrency;
	@JsonProperty("ask_currency")
	private String askCurrency;
	@JsonProperty("purchase_price")
	private String purchasePrice;
	@JsonProperty("selling_price")
	private String sellingPrice;
	@JsonProperty("market_identifier")
	private String marketIdentifier;
}
