package com.wenance.challenge.argbitcoin;

import com.wenance.challenge.argbitcoin.domain.dto.ArgBitcoinDTO;
import com.wenance.challenge.argbitcoin.domain.dto.BuenbitObjectResponseDTO;
import com.wenance.challenge.argbitcoin.service.impl.ArgBitcoinServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArgbitcoinApplicationTests {

	@Autowired
	private WebTestClient client;

	@Autowired
	private ArgBitcoinServiceImpl argBitcoinService;

	@Test
	void contextLoads() {
	}

	@Test
	void getBitcoinArgSellingPriceTest() {
		client.get()
				.uri("/api/arg-bitcoin/selling-price?date={date}", Collections.singletonMap("date", "2021-03-04T04:51:32"))
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(ArgBitcoinDTO.class);

	}

	@Test
	void getBitcoinArgAvgSellingPriceOKTest() {
		String from = "2021-01-01T00:00:00";
		String to = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.of("America/Argentina/Buenos_Aires")).format(Instant.now());
		HashMap<String, String> param = new HashMap<>();
		param.put("from", from);
		param.put("to", to);
		int page = 0;

		List<BuenbitObjectResponseDTO> totalList = new ArrayList<>();
		List<BuenbitObjectResponseDTO> partialList = new ArrayList<>();

		do {
			partialList = argBitcoinService.getCryptoCurrencyData(from, to, page).collectList().block();
			totalList.addAll(partialList);
			page++;
		} while (partialList.size() > 0);

		OptionalDouble avg = totalList.stream()
				.mapToDouble(e -> Double.parseDouble(e.getBtcars().getSellingPrice()))
				.average();

		client.get()
				.uri("/api/arg-bitcoin/selling-price/average?fromDate={from}&toDate={to}", param)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBody(ArgBitcoinDTO.class)
				.consumeWith( r -> {
					Assertions.assertEquals(avg.getAsDouble(), r.getResponseBody().getSellingPrice());
				});

	}

	@Test
	void getBitcoinDataWithDateRangeTest() {
		String from = "2021-01-01T00:00:00";
		String to = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.of("America/Argentina/Buenos_Aires")).format(Instant.now());
		HashMap<String, String> param = new HashMap<>();
		param.put("from", from);
		param.put("to", to);
		client.get()
				.uri("/api/arg-bitcoin/data?fromDate={from}&toDate={to}&page=0", param)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(BuenbitObjectResponseDTO.class);
	}

	@Test
	void getBitcoinDataWithOutDateRangeTest() {
		client.get()
				.uri("/api/arg-bitcoin/data?page=0")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(BuenbitObjectResponseDTO.class);

	}


}
