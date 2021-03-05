package com.wenance.challenge.argbitcoin.controller;

import com.wenance.challenge.argbitcoin.domain.dto.ArgBitcoinDTO;
import com.wenance.challenge.argbitcoin.domain.dto.BuenbitObjectResponseDTO;
import com.wenance.challenge.argbitcoin.exception.BadRequestException;
import com.wenance.challenge.argbitcoin.service.ArgBitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

import static com.wenance.challenge.argbitcoin.domain.constant.Constants.DATE_FORMAT_MSG;
import static com.wenance.challenge.argbitcoin.domain.constant.Constants.DATE_FORMAT_PATTERN;
import static org.springframework.util.StringUtils.hasLength;

@RestController
@RequestMapping("/api/arg-bitcoin")
@Validated
public class ArgBitcoinController {

	@Autowired
	@Qualifier("argBitcoinService")
	ArgBitcoinService argBitcoinService;

	@GetMapping("/selling-price")
	Mono<ArgBitcoinDTO> getBitcoinArgSellingPrice(@RequestParam @Pattern(regexp = DATE_FORMAT_PATTERN, message = DATE_FORMAT_MSG) String date) {

		LocalDateTime dateParam = LocalDateTime.parse(date);

		return argBitcoinService.getArgBitcoinSellingPrice(dateParam);
	}

	@GetMapping("/selling-price/average")
	Mono<ArgBitcoinDTO> getBitcoinArgAvgSellingPrice(@RequestParam @Pattern(regexp = DATE_FORMAT_PATTERN, message = DATE_FORMAT_MSG) String fromDate,
													 @RequestParam @Pattern(regexp = DATE_FORMAT_PATTERN, message = DATE_FORMAT_MSG) String toDate) {
		return argBitcoinService.getArgBitcoinAverageSellingPrice(LocalDateTime.parse(fromDate), LocalDateTime.parse(toDate));
	}

	@GetMapping("/data")
	Flux<BuenbitObjectResponseDTO> getBitcoinData(@RequestParam(required = false) @Pattern(regexp = DATE_FORMAT_PATTERN, message = DATE_FORMAT_MSG) String fromDate,
												  @RequestParam(required = false) @Pattern(regexp = DATE_FORMAT_PATTERN, message = DATE_FORMAT_MSG) String toDate,
												  @RequestParam int page) throws Exception {
		validateNullInputs(fromDate, toDate);
		return argBitcoinService.getCryptoCurrencyData(fromDate, toDate, page);
	}

	private void validateNullInputs(String fromDate, String toDate) throws BadRequestException {
		if (!((hasLength(fromDate) && hasLength(toDate)) || (!hasLength(fromDate) && !hasLength(toDate)))) {
			throw new BadRequestException();
		}
	}


}
