package com.wenance.challenge.argbitcoin.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArgBitcoinDTO {
	Double sellingPrice;

	public ArgBitcoinDTO(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
}
