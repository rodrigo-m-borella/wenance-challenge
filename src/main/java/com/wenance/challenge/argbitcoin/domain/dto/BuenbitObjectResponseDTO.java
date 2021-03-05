package com.wenance.challenge.argbitcoin.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class BuenbitObjectResponseDTO {

	private BtcDaiDTO daiars;
	private BtcDaiDTO daiusd;
	private BtcDaiDTO btcars;
	private LocalDateTime date;
}
